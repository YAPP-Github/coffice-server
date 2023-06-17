package kr.co.yapp._22nd.coffice.ui.place;

import kr.co.yapp._22nd.coffice.domain.place.Place;
import kr.co.yapp._22nd.coffice.domain.place.PlaceSearchResponseVo;
import org.springframework.stereotype.Component;

@Component
public class PlaceAssembler {
    public PlaceResponse toPlaceResponse(Place place) {
        return new PlaceResponse(
                place.getPlaceId(),
                place.getName(),
                CoordinatesResponse.from(place.getCoordinates()),
                place.getAddress() == null ? null : AddressResponse.from(place.getAddress())
        );
    }

    public PlaceResponse toPlaceResponse(PlaceSearchResponseVo placeSearchResponseVo) {
        return new PlaceResponse(
                placeSearchResponseVo.getPlaceId(),
                placeSearchResponseVo.getName(),
                CoordinatesResponse.from(placeSearchResponseVo.getCoordinates()),
                placeSearchResponseVo.getAddress() == null ? null : AddressResponse.from(placeSearchResponseVo.getAddress())
        );
    }
}
