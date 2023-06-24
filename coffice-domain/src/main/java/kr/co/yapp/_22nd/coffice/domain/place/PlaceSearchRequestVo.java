package kr.co.yapp._22nd.coffice.domain.place;

import lombok.Value;

import java.util.Set;

@Value(staticConstructor = "of")
public class PlaceSearchRequestVo {
    String searchText;
    Coordinates coordinates;
    Distance distance;
    Boolean open;
    Boolean hasCommunalTable;
    Set<CapacityLevel> capacityLevels;
}
