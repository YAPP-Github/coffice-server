package kr.co.yapp._22nd.coffice.ui.place;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import kr.co.yapp._22nd.coffice.domain.place.CapacityLevel;
import kr.co.yapp._22nd.coffice.domain.place.DrinkType;
import kr.co.yapp._22nd.coffice.domain.place.FoodType;
import kr.co.yapp._22nd.coffice.domain.place.RestroomType;
import lombok.Data;

import java.util.Set;

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
    private Set<CapacityLevel> capacityLevels;
    private Set<DrinkType> drinkTypes;
    private Set<FoodType> foodTypes;
    private Set<RestroomType> restroomTypes;
    @Min(1)
    @NotNull
    private Integer pageSize;
    @Nullable
    @Min(0)
    private Double lastSeenDistance;
}
