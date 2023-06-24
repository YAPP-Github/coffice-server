package kr.co.yapp._22nd.coffice.domain.place;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class PlaceRepositoryImpl extends QuerydslRepositorySupport implements PlaceRepositoryCustom {
    private final QPlace place = QPlace.place;
    private final QOpeningHour openingHour = QOpeningHour.openingHour;

    public PlaceRepositoryImpl() {
        super(Place.class);
    }

    @Override
    public Page<PlaceSearchResponseVo> findByCoordinatesAndDistanceLessThan(
            PlaceSearchRequestVo placeSearchRequestVo,
            Pageable pageable
    ) {
        var name = placeSearchRequestVo.getSearchText();
        var coordinates = placeSearchRequestVo.getCoordinates();
        var distance = placeSearchRequestVo.getDistance();
        var open = placeSearchRequestVo.getOpen();

        NumberExpression<Double> distanceExpression = Expressions.numberTemplate(
                Double.class,
                "(6371 * acos(cos(radians({0})) * cos(radians({1}.latitude)) * cos(radians({1}.longitude) - radians({2})) + sin(radians({0})) * sin(radians({1}.latitude))))",
                coordinates.getLatitude(),
                place.coordinates,
                coordinates.getLongitude()
        );
        BooleanExpression booleanExpression = distanceExpression.loe(distance.toKilometerValue());
        if (StringUtils.hasText(name)) {
            booleanExpression = booleanExpression.and(place.name.contains(name));
        }
        if (open == Boolean.TRUE) {
            booleanExpression = booleanExpression.and(getOpenCondition());
        }
        var queryResults = from(place)
                .leftJoin(place.openingHours, openingHour)
                .select(
                        place.placeId,
                        place.name,
                        place.coordinates.latitude,
                        place.coordinates.longitude,
                        place.address.streetAddress,
                        place.address.landAddress,
                        place.address.postalCode,
                        distanceExpression
                )
                .where(booleanExpression)
                .orderBy(distanceExpression.asc())
                .distinct()
                .fetchResults();
        List<PlaceSearchResponseVo> placeSearchResponseVos = queryResults.getResults().stream()
                .map(it -> PlaceSearchResponseVo.of(
                                it.get(place.placeId),
                                it.get(place.name),
                                Coordinates.of(
                                        it.get(place.coordinates.latitude),
                                        it.get(place.coordinates.longitude)
                                ),
                                Address.builder()
                                        .streetAddress(it.get(place.address.streetAddress))
                                        .landAddress(it.get(place.address.landAddress))
                                        .postalCode(it.get(place.address.postalCode))
                                        .build(),
                                it.get(place.openingHours) != null
                                        ? it.get(place.openingHours)
                                        : Collections.emptyList(),
                                ElectricOutletLevel.of(
                                        it.get(place.electricOutletCount),
                                        it.get(place.seatCount)
                                ),
                                Distance.of(
                                        it.get(distanceExpression),
                                        DistanceUnit.KILOMETER
                                )
                        )
                )
                .toList();
        return PageableExecutionUtils.getPage(
                placeSearchResponseVos,
                pageable,
                queryResults::getTotal
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
        BooleanExpression isOpeningDay = openingHour.openingHoursType.eq(OpeningHourType.OPEN);
        BooleanExpression isOnOpeningHours = openingHour.openedAt.lt(now.toLocalTime())
                .and(openingHour.closedAt.goe(now.toLocalTime()));
        BooleanExpression isOpen24Hours = openingHour.openAroundTheClock.isTrue();
        return isOpeningDay.and(isOnOpeningHours.or(isOpen24Hours));
    }
}
