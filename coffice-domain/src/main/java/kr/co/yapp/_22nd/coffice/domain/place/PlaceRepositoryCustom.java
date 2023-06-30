package kr.co.yapp._22nd.coffice.domain.place;

import kr.co.yapp._22nd.coffice.domain.CursorPageable;
import org.springframework.data.domain.Slice;

public interface PlaceRepositoryCustom {

    Slice<PlaceSearchResponseVo> findByCoordinatesAndDistanceLessThan(
            PlaceSearchRequestVo placeSearchRequestVo,
            CursorPageable<Long> cursorPageable
    );
}
