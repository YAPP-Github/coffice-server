package kr.co.yapp.cafe.domain.place;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;

    @Override
    @Transactional
    public Place create(PlaceCreateVo placeCreateVo) {
        Place place = Place.from(placeCreateVo);
        return placeRepository.save(place);
    }

    @Override
    @Transactional
    public Place update(Long placeId, PlaceUpdateVo placeUpdateVo) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceNotFoundException(placeId));
        place.update(placeUpdateVo);
        return place;
    }

    @Override
    public Page<Place> findAll(Pageable pageable) {
        return placeRepository.findByDeletedFalse(pageable);
    }

    @Override
    public Optional<Place> findById(Long placeId) {
        return placeRepository.findByPlaceIdAndDeletedFalse(placeId);
    }
}
