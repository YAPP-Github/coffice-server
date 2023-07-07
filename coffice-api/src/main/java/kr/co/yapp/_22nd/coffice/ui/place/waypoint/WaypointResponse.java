package kr.co.yapp._22nd.coffice.ui.place.waypoint;

import kr.co.yapp._22nd.coffice.ui.place.CoordinatesResponse;
import lombok.Data;

@Data
public class WaypointResponse {
    private final Long waypointId;
    private final String name;
    private final CoordinatesResponse coordinates;
}
