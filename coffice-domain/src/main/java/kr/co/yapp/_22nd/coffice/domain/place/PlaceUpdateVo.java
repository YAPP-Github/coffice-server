package kr.co.yapp._22nd.coffice.domain.place;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class PlaceUpdateVo {
    String name;
    Coordinates coordinates;
    Address address;
    List<OpeningHour> openingHours;
}
