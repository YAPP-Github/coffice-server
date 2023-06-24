package kr.co.yapp._22nd.coffice.domain.place;

import lombok.Value;

@Value(staticConstructor = "of")
public class PlaceSearchRequestVo {
    String searchText;
    Coordinates coordinates;
    Distance distance;
    Boolean open;
    Boolean hasCommunalTable;
}
