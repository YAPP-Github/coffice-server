package kr.co.yapp._22nd.coffice.ui.place;

import kr.co.yapp._22nd.coffice.domain.place.*;
import kr.co.yapp._22nd.coffice.ui.DateTimeAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlaceSearchAssembler {
    private final PlaceAssembler placeAssembler;
    private final DateTimeAssembler dateTimeAssembler;

    public PlaceSearchRequestVo toPlaceSearchRequestVo(
            PlaceSearchRequest placeSearchRequest
    ) {
        return PlaceSearchRequestVo.of(
                placeSearchRequest.getSearchText(),
                Coordinates.of(
                        placeSearchRequest.getLatitude(),
                        placeSearchRequest.getLongitude()
                ),
                Distance.of(
                        placeSearchRequest.getDistance(),
                        DistanceUnit.METER
                ),
                placeSearchRequest.getOpen(),
                placeSearchRequest.getHasCommunalTable(),
                placeSearchRequest.getCapacityLevels(),
                placeSearchRequest.getDrinkTypes(),
                placeSearchRequest.getFoodTypes(),
                placeSearchRequest.getRestroomTypes()
        );
    }

    public PlaceSearchResponse toPlaceSearchResponse(
            PlaceSearchResponseVo placeSearchResponseVo
    ) {
        return new PlaceSearchResponse(
                placeSearchResponseVo.getPlaceId(),
                placeSearchResponseVo.getName(),
                CoordinatesResponse.from(placeSearchResponseVo.getCoordinates()),
                placeSearchResponseVo.getAddress() == null ? null : AddressResponse.from(placeSearchResponseVo.getAddress()),
                placeSearchResponseVo.getHomepageUrl(),
                placeSearchResponseVo.getOpeningHours()
                        .stream()
                        .map(it -> OpeningHourResponse.of(it, dateTimeAssembler))
                        .collect(Collectors.toList()),
                placeSearchResponseVo.getPhoneNumber().getValue(),
                placeSearchResponseVo.getElectricOutletLevel().name(),
                placeSearchResponseVo.getHasCommunalTable(),
                placeSearchResponseVo.getCapacityLevel().name(),
                placeSearchResponseVo.getImageUrls(),
                placeAssembler.toDTO(placeSearchResponseVo.getCrowdednessList()),
                placeSearchResponseVo.getFoodTypes().stream().map(Enum::name).collect(Collectors.toList()),
                placeSearchResponseVo.getRestroomTypes().stream().map(Enum::name).collect(Collectors.toList()),
                placeSearchResponseVo.getDrinkTypes().stream().map(Enum::name).collect(Collectors.toList()),
                placeSearchResponseVo.getDistance().getValue().doubleValue()
        );
    }
}
