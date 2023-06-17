package kr.co.yapp._22nd.coffice.ui.place;

import kr.co.yapp._22nd.coffice.domain.place.Place;
import kr.co.yapp._22nd.coffice.domain.place.PlaceCreateVo;
import kr.co.yapp._22nd.coffice.domain.place.PlaceUpdateVo;
import org.springframework.stereotype.Component;

@Component
public class PlaceAssembler {
    public PlaceResponse toPlaceResponse(Place place) {
        return new PlaceResponse(
                place.getPlaceId(),
                place.getName()
        );
    }

    public PlaceCreateVo toPlaceCreateVo(PlaceCreateRequest placeCreateRequest) {
        return PlaceCreateVo.of(
                placeCreateRequest.getName()
        );
    }

    public PlaceUpdateVo toPlaceUpdateVo(PlaceUpdateRequest placeUpdateRequest) {
        return PlaceUpdateVo.of(
                placeUpdateRequest.getName()
        );
    }
}
