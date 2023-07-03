package kr.co.yapp._22nd.coffice.domain.place;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class PlaceSearchResponseVo {
    Long placeId;
    String name;
    Coordinates coordinates;
    Address address;
    List<OpeningHour> openingHours;
    PhoneNumber phoneNumber;
    String homepageUrl;
    ElectricOutlet electricOutlet;
    Boolean hasCommunalTable;
    CapacityLevel capacityLevel;
    List<String> imageUrls;
    List<Crowdedness> crowdednessList;
    List<DrinkType> drinkTypes;
    List<FoodType> foodTypes;
    List<RestroomType> restroomTypes;
    Distance distance;
}
