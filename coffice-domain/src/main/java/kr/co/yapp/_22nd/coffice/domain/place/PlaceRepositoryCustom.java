package kr.co.yapp._22nd.coffice.domain.place;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaceRepositoryCustom {

    Page<PlaceSearchResponseVo> findByCoordinatesAndDistanceLessThan(
            String name,
            Coordinates coordinates,
            Distance distance,
            Pageable pageable
    );
}
