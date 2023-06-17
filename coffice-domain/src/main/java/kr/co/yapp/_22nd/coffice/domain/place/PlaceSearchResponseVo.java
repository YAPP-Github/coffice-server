package kr.co.yapp._22nd.coffice.domain.place;

import lombok.Value;

@Value(staticConstructor = "of")
public class PlaceSearchResponseVo {
    Long placeId;
    String name;
    Coordinates coordinates;
    Distance distance;
}
