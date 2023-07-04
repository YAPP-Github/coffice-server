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
    Boolean archived;
    Distance distance;

    public PlaceSearchResponseVo copyOf(boolean archived) {
        return new PlaceSearchResponseVo(
                placeId,
                name,
                coordinates,
                address,
                openingHours,
                phoneNumber,
                homepageUrl,
                electricOutlet,
                hasCommunalTable,
                capacityLevel,
                imageUrls,
                crowdednessList,
                drinkTypes,
                foodTypes,
                restroomTypes,
                archived,
                distance
        );
    }
}
