package kr.co.yapp._22nd.coffice.ui.place;

import kr.co.yapp._22nd.coffice.application.PlaceArchiveApplicationService;
import kr.co.yapp._22nd.coffice.domain.place.*;
import kr.co.yapp._22nd.coffice.ui.DateTimeAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class PlaceAssembler {
    private final DateTimeAssembler dateTimeAssembler;
    private final PlaceArchiveApplicationService placeArchiveApplicationService;

    public PlaceQueryRequestVo toPlaceQueryRequestVo(
            String name
    ) {
        return PlaceQueryRequestVo.of(name);
    }

    public Stream<PlaceResponse> toPlaceResponses(Long memberId, Stream<Place> placeStream) {
        Set<Long> archivedPlaceIds = placeArchiveApplicationService.getArchivedPlaces(memberId)
                .stream()
                .map(Place::getPlaceId)
                .collect(Collectors.toSet());
        return placeStream.map(it -> convertToPlaceResponse(it, archivedPlaceIds.contains(it.getPlaceId())));
    }

    public Slice<PlaceResponse> toPlaceResponses(Long memberId, Slice<Place> places) {
        Set<Long> archivedPlaceIds = placeArchiveApplicationService.getArchivedPlaces(memberId)
                .stream()
                .map(Place::getPlaceId)
                .collect(Collectors.toSet());
        return places.map(it -> convertToPlaceResponse(it, archivedPlaceIds.contains(it.getPlaceId())));
    }

    public PlaceResponse toPlaceResponse(Long memberId, Place place) {
        boolean archived = placeArchiveApplicationService.isArchivedPlace(memberId, place.getPlaceId());
        return convertToPlaceResponse(place, archived);
    }

    private PlaceResponse convertToPlaceResponse(Place place, boolean archived) {
        return new PlaceResponse(
                place.getPlaceId(),
                place.getName(),
                CoordinatesResponse.from(place.getCoordinates()),
                place.getAddress() == null ? null : AddressResponse.from(place.getAddress()),
                place.getHomepageUrl(),
                place.getOpeningHours()
                        .stream()
                        .map(it -> OpeningHourResponse.of(it, dateTimeAssembler))
                        .collect(Collectors.toList()),
                place.getPhoneNumber() != null ? place.getPhoneNumber().getValue() : null,
                place.getElectricOutletLevel().name(),
                place.hasCommunalTable(),
                place.getCapacityLevel().name(),
                place.getImageUrls(),
                toDTO(place.getCrowdednessList()),
                place.getDrinkTypes().stream().map(Enum::name).collect(Collectors.toList()),
                place.getFoodTypes().stream().map(Enum::name).collect(Collectors.toList()),
                place.getRestroomTypes().stream().map(Enum::name).collect(Collectors.toList()),
                archived
        );
    }

    public List<CrowdednessResponse> toDTO(List<Crowdedness> crowdednessList) {
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
