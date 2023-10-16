package kr.co.yapp._22nd.coffice.domain.place.report;

import kr.co.yapp._22nd.coffice.domain.place.*;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class PlaceReportCreateVo {
    Coordinates coordinates;
    String name;
    Address address;
    PhoneNumber phoneNumber;
    ElectricOutletLevel electricOutletLevel;
    CapacityLevel capacityLevel;
    Boolean hasCommunalTable;
    List<String> imageUrls;
    List<FoodType> foodTypes;
    List<RestroomType> restroomTypes;
    List<DrinkType> drinkTypes;
    String text;
}
