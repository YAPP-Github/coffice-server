package kr.co.yapp._22nd.coffice.ui.place;

import kr.co.yapp._22nd.coffice.domain.place.*;
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

    public PlaceResponse toPlaceResponse(PlaceSearchResponseVo placeSearchResponseVo) {
        return new PlaceResponse(
                placeSearchResponseVo.getPlaceId(),
                placeSearchResponseVo.getName(),
                CoordinatesResponse.from(placeSearchResponseVo.getCoordinates())
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
