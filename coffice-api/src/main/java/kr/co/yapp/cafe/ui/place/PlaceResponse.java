package kr.co.yapp.cafe.ui.place;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlaceResponse {
    @NotNull
    private final Long placeId;
    @NotBlank
    private final String name;
}
