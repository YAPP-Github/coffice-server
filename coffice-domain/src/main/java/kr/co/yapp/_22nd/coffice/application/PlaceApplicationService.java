package kr.co.yapp._22nd.coffice.application;

import kr.co.yapp._22nd.coffice.domain.CursorPageable;
import kr.co.yapp._22nd.coffice.domain.place.*;
import kr.co.yapp._22nd.coffice.domain.search.SearchRequestedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceApplicationService {
    private final PlaceCommandService placeCommandService;
    private final PlaceQueryService placeQueryService;
    private final SearchRequestedEventPublisher searchRequestedEventPublisher;

    public Place create(PlaceCreateVo placeCreateVo) {
        return placeCommandService.create(placeCreateVo);
    }

    public Place update(Long placeId, PlaceUpdateVo placeUpdateVo) {
        return placeCommandService.update(placeId, placeUpdateVo);
    }

    public Slice<PlaceSearchResponseVo> search(
            Long memberId,
            PlaceSearchRequestVo placeSearchRequestVo,
            CursorPageable<Long> cursorPageable
    ) {
        searchRequestedEventPublisher.publish(
                new SearchRequestedEvent(
                        memberId,
                        placeSearchRequestVo.getSearchText()
                )
        );
        return placeQueryService.search(
                placeSearchRequestVo,
                cursorPageable
        );
    }

    public Page<Place> findAll(Pageable pageable) {
        return placeQueryService.findAll(pageable);
    }

    public Optional<Place> findById(Long placeId) {
        return placeQueryService.findById(placeId);
    }

    public Place getPlace(Long placeId) {
        return placeQueryService.getPlace(placeId);
    }

}
