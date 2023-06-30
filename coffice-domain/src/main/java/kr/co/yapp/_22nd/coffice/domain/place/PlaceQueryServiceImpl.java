package kr.co.yapp._22nd.coffice.domain.place;

import kr.co.yapp._22nd.coffice.domain.CursorPageable;
import kr.co.yapp._22nd.coffice.domain.place.folder.place.PlaceFolderPlace;
import kr.co.yapp._22nd.coffice.domain.place.folder.place.PlaceFolderPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceQueryServiceImpl implements PlaceQueryService {
    private final PlaceRepository placeRepository;
    private final PlaceFolderPlaceRepository placeFolderPlaceRepository;

    @Override
    public Page<Place> findAll(Pageable pageable) {
        return placeRepository.findByDeletedFalse(pageable);
    }

    @Override
    public Optional<Place> findById(Long placeId) {
        return placeRepository.findByPlaceIdAndDeletedFalse(placeId);
    }

    @Override
    public Place getPlace(Long placeId) {
        return placeRepository.findByPlaceIdAndDeletedFalse(placeId)
                .orElseThrow(() -> new PlaceNotFoundException(placeId));
    }

    @Override
    public Slice<PlaceSearchResponseVo> search(
            PlaceSearchRequestVo placeSearchRequestVo,
            CursorPageable<Long> pageable
    ) {
        return placeRepository.findByCoordinatesAndDistanceLessThan(placeSearchRequestVo, pageable);
    }

    @Override
    public List<Place> findByPlaceFolderId(Long memberId, Long placeFolderId) {
        return placeFolderPlaceRepository.findByMember_memberIdAndPlaceFolder_placeFolderId(memberId, placeFolderId)
                .stream()
                .map(PlaceFolderPlace::getPlace)
                .collect(Collectors.toList());
    }
}
