package kr.co.yapp._22nd.coffice.ui.place;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class PlaceSearchRequest {
    private String searchText;
    @Positive
    private Double latitude;
    @Positive
    private Double longitude;
    @PositiveOrZero
    @NotNull
    private Double distance;
    @Min(0)
    @NotNull
    private Integer pageSize;
    @Min(1)
    @NotNull
    private Integer pageNumber;
}
