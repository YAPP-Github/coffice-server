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
    /**
     * 영업중
     */
    private Boolean open;
    /**
     * 단체 테이블 있는지
     */
    private Boolean hasCommunalTable;
    @Min(1)
    @NotNull
    private Integer pageSize;
    @Min(0)
    @NotNull
    private Integer pageNumber;
}
