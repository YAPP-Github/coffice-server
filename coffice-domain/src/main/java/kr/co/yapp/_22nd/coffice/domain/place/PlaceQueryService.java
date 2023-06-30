package kr.co.yapp._22nd.coffice.domain.place;

import kr.co.yapp._22nd.coffice.domain.CursorPageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface PlaceQueryService {
    Page<Place> findAll(Pageable pageable);

    Optional<Place> findById(Long placeId);

    Place getPlace(Long placeId);

    Slice<PlaceSearchResponseVo> search(PlaceSearchRequestVo placeSearchVo, CursorPageable<Long> cursorPageable);

    List<Place> findByPlaceFolderId(Long memberId, Long placeFolderId);
}


