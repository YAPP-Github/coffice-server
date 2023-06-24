package kr.co.yapp._22nd.coffice.ui.place;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PlaceResponse {
    @NotNull
    private final Long placeId;
    @NotBlank
    private final String name;
    @NotNull
    private final CoordinatesResponse coordinates;
    private final AddressResponse address;
    private final List<OpeningHourResponse> openingHours;
    private final String electricOutletLevel;
    private final Boolean hasCommunalTable;
}
