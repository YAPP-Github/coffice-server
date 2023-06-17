package kr.co.yapp._22nd.coffice.domain.place;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceQueryServiceImpl implements PlaceQueryService {
    private final PlaceRepository placeRepository;

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
    public Page<PlaceSearchResponseVo> search(
            PlaceSearchRequestVo placeSearchRequestVo,
            Pageable pageable
    ) {
        return placeRepository.findByCoordinatesAndDistanceLessThan(
                placeSearchRequestVo.getSearchText(),
                placeSearchRequestVo.getCoordinates(),
                placeSearchRequestVo.getDistance(),
                pageable
        );
    }
}
