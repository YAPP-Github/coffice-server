package kr.co.yapp._22nd.coffice.domain.place;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PlaceQueryService {
    Page<Place> findAll(Pageable pageable);

    Optional<Place> findById(Long placeId);

    Place getPlace(Long placeId);

    Page<PlaceSearchResponseVo> search(PlaceSearchRequestVo placeSearchVo, Pageable pageable);
}


