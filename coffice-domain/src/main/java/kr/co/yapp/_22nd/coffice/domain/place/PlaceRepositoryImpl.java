package kr.co.yapp._22nd.coffice.domain.place;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

public class PlaceRepositoryImpl extends QuerydslRepositorySupport implements PlaceRepositoryCustom {
    private final QPlace place = QPlace.place;

    public PlaceRepositoryImpl() {
        super(Place.class);
    }

    @Override
    public Page<PlaceSearchResponseVo> findByCoordinatesAndDistanceLessThan(
            String name,
            Coordinates coordinates,
            Distance distance,
            Pageable pageable
    ) {
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
        QueryResults<Tuple> queryResults = from(place)
                .where(booleanExpression)
                .orderBy(distanceExpression.asc())
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
}
