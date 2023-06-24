package kr.co.yapp._22nd.coffice.ui.place;

import kr.co.yapp._22nd.coffice.domain.place.Place;
import kr.co.yapp._22nd.coffice.domain.place.PlaceSearchResponseVo;
import kr.co.yapp._22nd.coffice.ui.DateTimeAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlaceAssembler {
    private final DateTimeAssembler dateTimeAssembler;

    public PlaceResponse toPlaceResponse(Place place) {
        return new PlaceResponse(
                place.getPlaceId(),
                place.getName(),
                CoordinatesResponse.from(place.getCoordinates()),
                place.getAddress() == null ? null : AddressResponse.from(place.getAddress()),
                place.getOpeningHours()
                        .stream()
                        .map(it -> OpeningHourResponse.of(it, dateTimeAssembler))
                        .collect(Collectors.toList())
        );
    }

    public PlaceResponse toPlaceResponse(PlaceSearchResponseVo placeSearchResponseVo) {
        return new PlaceResponse(
                placeSearchResponseVo.getPlaceId(),
                placeSearchResponseVo.getName(),
                CoordinatesResponse.from(placeSearchResponseVo.getCoordinates()),
                placeSearchResponseVo.getAddress() == null ? null : AddressResponse.from(placeSearchResponseVo.getAddress()),
                placeSearchResponseVo.getOpeningHours()
                        .stream()
                        .map(it -> OpeningHourResponse.of(it, dateTimeAssembler))
                        .collect(Collectors.toList())
        );
    }
}
