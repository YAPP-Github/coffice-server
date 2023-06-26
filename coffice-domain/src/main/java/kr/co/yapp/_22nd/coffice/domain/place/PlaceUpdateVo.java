package kr.co.yapp._22nd.coffice.domain.place;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class PlaceUpdateVo {
    String name;
    Coordinates coordinates;
    Address address;
    List<OpeningHour> openingHours;
    PhoneNumber phoneNumber;
    String homepageUrl;
    ElectricOutletCount electricOutletCount;
    SeatCount seatCount;
    TableCount tableCount;
    CommunalTableCount communalTableCount;
    List<String> imageUrls;
    List<Crowdedness> crowdednessList;
    List<DrinkType> drinkTypes;
    List<FoodType> foodTypes;
    List<RestroomType> restroomTypes;
}
