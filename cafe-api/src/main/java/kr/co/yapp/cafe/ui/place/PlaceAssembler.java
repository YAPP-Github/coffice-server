package kr.co.yapp.cafe.ui.place;

import kr.co.yapp.cafe.domain.place.Place;
import kr.co.yapp.cafe.domain.place.PlaceCreateVo;
import kr.co.yapp.cafe.domain.place.PlaceUpdateVo;
import org.springframework.stereotype.Component;

@Component
public class PlaceAssembler {
    public PlaceResponse toPlaceResponse(Place place) {
        return new PlaceResponse(
                place.getId(),
                place.getName()
        );
    }

    public PlaceCreateVo toPlaceCreateVo(PlaceCreateRequest placeCreateRequest) {
        return new PlaceCreateVo(
                placeCreateRequest.getName()
        );
    }

    public PlaceUpdateVo toPlaceUpdateVo(PlaceUpdateRequest placeUpdateRequest) {
        return new PlaceUpdateVo(
                placeUpdateRequest.getName()
        );
    }
}
