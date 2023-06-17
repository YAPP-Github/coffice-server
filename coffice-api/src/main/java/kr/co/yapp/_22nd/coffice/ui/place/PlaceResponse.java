package kr.co.yapp._22nd.coffice.ui.place;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlaceResponse {
    @NotNull
    private final Long placeId;
    @NotBlank
    private final String name;
    @NotNull
    private final CoordinatesResponse coordinates;
}
