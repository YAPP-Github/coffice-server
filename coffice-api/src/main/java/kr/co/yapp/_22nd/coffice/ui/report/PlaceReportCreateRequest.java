package kr.co.yapp._22nd.coffice.ui.report;

import jakarta.validation.constraints.NotNull;
import kr.co.yapp._22nd.coffice.domain.place.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PlaceReportCreateRequest {
    @NotNull
    private Double mapx;
    @NotNull
    private Double mapy;
    @NotNull
    private String name;
    @NotNull
    private String streetAddress;
    private String landAddress;
    @NotNull
    private String phoneNumber;
    @NotNull
    private ElectricOutletLevel electricOutletLevel;
    @NotNull
    private CapacityLevel capacityLevel;
    @NotNull
    private Boolean hasCommunalTable;
    private List<String> imageUrls;
    private List<FoodType> foodTypes;
    private List<RestroomType> restroomTypes;
    private List<DrinkType> drinkTypes;
    private String text;

}
