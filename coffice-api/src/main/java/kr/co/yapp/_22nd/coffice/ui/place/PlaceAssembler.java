package kr.co.yapp._22nd.coffice.ui.place;

import kr.co.yapp._22nd.coffice.domain.place.Coordinates;
import kr.co.yapp._22nd.coffice.domain.place.Place;
import kr.co.yapp._22nd.coffice.domain.place.PlaceCreateVo;
import kr.co.yapp._22nd.coffice.domain.place.PlaceUpdateVo;
import org.springframework.stereotype.Component;

@Component
public class PlaceAssembler {
    public PlaceResponse toPlaceResponse(Place place) {
        return new PlaceResponse(
                place.getPlaceId(),
                place.getName(),
                CoordinatesResponse.from(place.getCoordinates())
        );
    }

    public PlaceCreateVo toPlaceCreateVo(PlaceCreateRequest placeCreateRequest) {
        return PlaceCreateVo.of(
                placeCreateRequest.getName(),
                Coordinates.of(
                        placeCreateRequest.getLatitude(),
                        placeCreateRequest.getLongitude()
                )
        );
    }

    public PlaceUpdateVo toPlaceUpdateVo(PlaceUpdateRequest placeUpdateRequest) {
        return PlaceUpdateVo.of(
                placeUpdateRequest.getName(),
                Coordinates.of(
                        placeUpdateRequest.getLatitude(),
                        placeUpdateRequest.getLongitude()
                )
        );
    }
}
