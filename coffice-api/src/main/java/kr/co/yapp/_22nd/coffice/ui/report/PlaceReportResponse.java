package kr.co.yapp._22nd.coffice.ui.report;

import kr.co.yapp._22nd.coffice.ui.place.AddressResponse;
import kr.co.yapp._22nd.coffice.ui.place.CoordinatesResponse;
import lombok.Data;

import java.util.List;

@Data
public class PlaceReportResponse {
    private final Long placeReportId;
    private final Long placeId;
    private final CoordinatesResponse coordinates;
    private final String name;
    private final AddressResponse address;
    private final String phoneNumber;
    private final String electricOutletLevel;
    private final String capacityLevel;
    private final Boolean hasCommunalTable;
    private final List<String> imageUrls;
    private final List<String> foodTypes;
    private final List<String> restroomTypes;
    private final List<String> drinkTypes;
    private final String text;
}
