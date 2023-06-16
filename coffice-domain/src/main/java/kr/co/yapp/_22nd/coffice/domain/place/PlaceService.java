package kr.co.yapp._22nd.coffice.domain.place;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PlaceService {
    Place create(PlaceCreateVo placeCreateVo);

    Place update(Long placeId, PlaceUpdateVo placeUpdateVo);

    Page<Place> findAll(Pageable pageable);

    Optional<Place> findById(Long placeId);
}
