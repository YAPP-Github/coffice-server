package kr.co.yapp._22nd.coffice.ui.place;

import kr.co.yapp._22nd.coffice.domain.place.*;
import kr.co.yapp._22nd.coffice.ui.DateTimeAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
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
                        .collect(Collectors.toList()),
                place.getPhoneNumber() != null ? place.getPhoneNumber().getValue() : null,
                place.getElectricOutletLevel().name(),
                place.hasCommunalTable(),
                place.getCapacityLevel().name(),
                place.getImageUrls(),
                toDTO(place.getCrowdednessList())
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
                        .collect(Collectors.toList()),
                placeSearchResponseVo.getPhoneNumber().getValue(),
                placeSearchResponseVo.getElectricOutletLevel().name(),
                placeSearchResponseVo.getHasCommunalTable(),
                placeSearchResponseVo.getCapacityLevel().name(),
                placeSearchResponseVo.getImageUrls(),
                toDTO(placeSearchResponseVo.getCrowdednessList())
        );
    }

    private List<CrowdednessResponse> toDTO(List<Crowdedness> crowdednessList) {
        if (CollectionUtils.isEmpty(crowdednessList)) {
            return unknown();
        }
        return crowdednessList
                .stream()
                .map(this::toCrowdednessResponse)
                .collect(Collectors.toList());
    }

    private CrowdednessResponse toCrowdednessResponse(Crowdedness crowdedness) {
        return new CrowdednessResponse(
                crowdedness.getWeekDayType().name(),
                crowdedness.getDayTimeType().name(),
                crowdedness.getCrowdednessLevel().name()
        );
    }

    private List<CrowdednessResponse> unknown() {
        return Arrays.stream(WeekDayType.values())
                .flatMap(weekDayType -> Arrays.stream(DayTimeType.values())
                        .map(dayTimeType -> new CrowdednessResponse(
                                weekDayType.name(),
                                dayTimeType.name(),
                                CrowdednessLevel.UNKNOWN.name()
                        )))
                .collect(Collectors.toList());
    }
}
