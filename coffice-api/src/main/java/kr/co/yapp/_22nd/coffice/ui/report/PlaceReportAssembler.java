package kr.co.yapp._22nd.coffice.ui.report;

import kr.co.yapp._22nd.coffice.domain.place.Address;
import kr.co.yapp._22nd.coffice.domain.place.Coordinates;
import kr.co.yapp._22nd.coffice.domain.place.PhoneNumber;
import kr.co.yapp._22nd.coffice.domain.place.report.PlaceReport;
import kr.co.yapp._22nd.coffice.domain.place.report.PlaceReportCreateVo;
import kr.co.yapp._22nd.coffice.ui.place.AddressResponse;
import kr.co.yapp._22nd.coffice.ui.place.CoordinatesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlaceReportAssembler {
    public PlaceReportCreateVo toPlaceReportCreateVo(
            PlaceReportCreateRequest placeReportCreateRequest,
            List<String> imageUrls
    ) {
        return PlaceReportCreateVo.of(
                Coordinates.convertFromKATEC(
                        placeReportCreateRequest.getMapy(),
                        placeReportCreateRequest.getMapx()
                ),
                placeReportCreateRequest.getName(),
                Address.builder()
                        .streetAddress(placeReportCreateRequest.getStreetAddress())
                        .landAddress(placeReportCreateRequest.getLandAddress())
                        .build(),
                PhoneNumber.from(placeReportCreateRequest.getPhoneNumber()),
                placeReportCreateRequest.getElectricOutletLevel(),
                placeReportCreateRequest.getCapacityLevel(),
                placeReportCreateRequest.getHasCommunalTable(),
                imageUrls,
                placeReportCreateRequest.getFoodTypes(),
                placeReportCreateRequest.getRestroomTypes(),
                placeReportCreateRequest.getDrinkTypes(),
                placeReportCreateRequest.getText()
        );
    }

    public PlaceReportResponse toPlaceReportResponse(
        PlaceReport placeReport
    ) {
        return new PlaceReportResponse(
                placeReport.getPlaceReportId(),
                placeReport.getPlaceId(),
                CoordinatesResponse.fromKATEC(
                        placeReport.getCoordinates()
                ),
                placeReport.getName(),
                AddressResponse.from(
                        placeReport.getAddress()
                ),
                placeReport.getPhoneNumber().getValue(),
                placeReport.getElectricOutletLevel().toString(),
                placeReport.getCapacityLevel().toString(),
                placeReport.getHasCommunalTable(),
                placeReport.getImageUrls(),
                placeReport.getFoodTypes()
                        .stream()
                        .map(Enum::toString)
                        .collect(Collectors.toList()),
                placeReport.getRestroomTypes()
                        .stream()
                        .map(Enum::toString)
                        .collect(Collectors.toList()),
                placeReport.getDrinkTypes()
                        .stream()
                        .map(Enum::toString)
                        .collect(Collectors.toList()),
                placeReport.getText()
        );
    }
}
