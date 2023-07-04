package kr.co.yapp._22nd.coffice.domain.place;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import kr.co.yapp._22nd.coffice.domain.CursorPageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class PlaceRepositoryImpl extends QuerydslRepositorySupport implements PlaceRepositoryCustom {
    private final QPlace qPlace = QPlace.place;
    private final QOpeningHour qOpeningHour = QOpeningHour.openingHour;

    public PlaceRepositoryImpl() {
        super(Place.class);
    }

    @Override
    public Slice<PlaceSearchResponseVo> findByCoordinatesAndDistanceLessThan(
            PlaceSearchRequestVo placeSearchRequestVo,
            CursorPageable<Double> cursorPageable
    ) {
        var name = placeSearchRequestVo.getSearchText();
        var coordinates = placeSearchRequestVo.getCoordinates();
        var distance = placeSearchRequestVo.getDistance();
        var open = placeSearchRequestVo.getOpen();
        var openAroundTheClock = placeSearchRequestVo.getOpenAroundTheClock();
        var hasCommunalTable = placeSearchRequestVo.getHasCommunalTable();
        var electricOutletLevels = placeSearchRequestVo.getElectricOutletLevels();
        var capacityLevels = placeSearchRequestVo.getCapacityLevels();
        var drinkTypes = placeSearchRequestVo.getDrinkTypes();
        var foodTypes = placeSearchRequestVo.getFoodTypes();
        var restroomTypes = placeSearchRequestVo.getRestroomTypes();

        NumberExpression<Double> distanceExpression = Expressions.numberTemplate(
                Double.class,
                "(6371 * acos(cos(radians({0})) * cos(radians({1}.latitude)) * cos(radians({1}.longitude) - radians({2})) + sin(radians({0})) * sin(radians({1}.latitude))))",
                coordinates.getLatitude(),
                qPlace.coordinates,
                coordinates.getLongitude()
        );
        BooleanExpression booleanExpression = distanceExpression.loe(distance.toKilometerValue());
        if (!cursorPageable.isInitial()) {
            Distance lastSeenDistance = Distance.of(cursorPageable.getLastSeenKey(), DistanceUnit.METER);
            booleanExpression = booleanExpression.and(distanceExpression.gt(lastSeenDistance.toKilometerValue()));
        }
        if (StringUtils.hasText(name)) {
            booleanExpression = booleanExpression.and(qPlace.name.contains(name));
        }
        if (open == Boolean.TRUE) {
            booleanExpression = booleanExpression.and(getOpenCondition());
        }
        if (openAroundTheClock == Boolean.TRUE) {
            booleanExpression = booleanExpression.and(getOpenAroundTheClockCondition());
        }
        if (hasCommunalTable == Boolean.TRUE) {
            booleanExpression = booleanExpression.and(qPlace.communalTableCount.value.gt(0));
        }
        if (!CollectionUtils.isEmpty(electricOutletLevels)) {
            booleanExpression = booleanExpression.and(getElectricOutletLevelCondition(electricOutletLevels));
        }
        if (!CollectionUtils.isEmpty(capacityLevels)) {
            booleanExpression = booleanExpression.and(getCapacityConditions(capacityLevels));
        }
        if (!CollectionUtils.isEmpty(drinkTypes)) {
            booleanExpression = booleanExpression.and(getDrinkTypeCondition(drinkTypes));
        }
        if (!CollectionUtils.isEmpty(foodTypes)) {
            booleanExpression = booleanExpression.and(getFoodTypeCondition(foodTypes));
        }
        if (!CollectionUtils.isEmpty(restroomTypes)) {
            booleanExpression = booleanExpression.and(getRestroomTypeCondition(restroomTypes));
        }
        var queryResults = from(qPlace)
                .leftJoin(qPlace.openingHours, qOpeningHour)
                .leftJoin(qPlace.imageUrls)
                .leftJoin(qPlace.drinkTypes)
                .leftJoin(qPlace.foodTypes)
                .leftJoin(qPlace.restroomTypes)
                .select(
                        qPlace,
                        distanceExpression
                )
                .where(booleanExpression)
                .orderBy(distanceExpression.asc(), qPlace.placeId.asc())
                .distinct()
                .limit(cursorPageable.getPageSize() + 1)
                .fetchResults();
        List<PlaceSearchResponseVo> placeSearchResponseVos = queryResults.getResults()
                .stream()
                .map(it -> {
                            Place place = Objects.requireNonNull(it.get(qPlace));
                            return PlaceSearchResponseVo.of(
                                    place.getPlaceId(),
                                    place.getName(),
                                    place.getCoordinates(),
                                    place.getAddress(),
                                    place.getOpeningHours(),
                                    place.getPhoneNumber(),
                                    place.getHomepageUrl(),
                                    place.getElectricOutlet(),
                                    place.hasCommunalTable(),
                                    place.getCapacityLevel(),
                                    place.getImageUrls(),
                                    place.getCrowdednessList(),
                                    place.getDrinkTypes(),
                                    place.getFoodTypes(),
                                    place.getRestroomTypes(),
                                    false,
                                    Distance.of(
                                            it.get(distanceExpression),
                                            DistanceUnit.KILOMETER
                                    )
                            );
                        }
                )
                .toList();
        boolean hasNext = placeSearchResponseVos.size() > cursorPageable.getPageSize();
        return new SliceImpl<>(
                hasNext ? placeSearchResponseVos.subList(0, cursorPageable.getPageSize()) : placeSearchResponseVos,
                cursorPageable.toPageable(),
                hasNext
        );
    }


    /**
     * 영업중 : 1,2,3 을 모두 만족해야함
     * 1. 현재시각으로 구한 요일에 맞는 영업시간 정보에 대해서
     * 2. 오늘이 영업일이고
     * 3. 아래 3-1, 3-2 중 하나만 만족해도 영업 중
     * 3-1 영업시작시각 <= 현재시각 && 현재시각 < 영업종료시각
     * 3-2 24시간 영업
     */
    private BooleanExpression getOpenCondition() {
        LocalDateTime now = LocalDateTime.now();
        BooleanExpression isOpeningDay = qOpeningHour.openingHoursType.eq(OpeningHourType.OPEN)
                .and(qOpeningHour.dayOfWeek.eq(now.getDayOfWeek()));
        BooleanExpression isOnOpeningHours = qOpeningHour.openedAt.lt(now.toLocalTime())
                .and(qOpeningHour.closedAt.goe(now.toLocalTime()));
        BooleanExpression isOpen24Hours = qOpeningHour.openAroundTheClock.isTrue();
        return isOpeningDay.and(isOnOpeningHours.or(isOpen24Hours));
    }

    private BooleanExpression getOpenAroundTheClockCondition() {
        LocalDateTime now = LocalDateTime.now();
        BooleanExpression isToday = qOpeningHour.dayOfWeek.eq(now.getDayOfWeek());
        BooleanExpression isOpen24Hours = qOpeningHour.openAroundTheClock.isTrue();
        return isToday.and(isOpen24Hours);
    }

    private Predicate getElectricOutletLevelCondition(Set<ElectricOutletLevel> electricOutletLevels) {
        Collection<Predicate> predicates = electricOutletLevels.stream()
                .map(qPlace.electricOutlet.level::eq)
                .map(it -> (Predicate) it)
                .toList();
        return ExpressionUtils.anyOf(predicates);
    }

    private Predicate getCapacityConditions(Collection<CapacityLevel> capacityLevels) {
        Collection<Predicate> predicates = capacityLevels.stream()
                .filter(it -> it != CapacityLevel.UNKNOWN)
                .map(it -> qPlace.tableCount.value.between(it.getFrom(), it.getTo()))
                .collect(Collectors.toList());
        return ExpressionUtils.anyOf(predicates);
    }

    private Predicate getDrinkTypeCondition(Set<DrinkType> drinkTypes) {
        List<Predicate> predicates = drinkTypes.stream()
                .map(qPlace.drinkTypes::contains)
                .map(it -> (Predicate) it)
                .toList();
        return ExpressionUtils.allOf(predicates);
    }

    private Predicate getFoodTypeCondition(Set<FoodType> foodTypes) {
        List<Predicate> predicates = foodTypes.stream()
                .map(qPlace.foodTypes::contains)
                .map(it -> (Predicate) it)
                .toList();
        return ExpressionUtils.allOf(predicates);
    }

    private Predicate getRestroomTypeCondition(Set<RestroomType> restroomTypes) {
        List<Predicate> predicates = restroomTypes.stream()
                .map(qPlace.restroomTypes::contains)
                .map(it -> (Predicate) it)
                .toList();
        return ExpressionUtils.allOf(predicates);
    }
}
