package kr.co.yapp._22nd.coffice.domain.place;

import lombok.Value;

@Value(staticConstructor = "of")
public class PlaceUpdateVo {
    String name;
    Coordinates coordinates;
    Address address;
}
