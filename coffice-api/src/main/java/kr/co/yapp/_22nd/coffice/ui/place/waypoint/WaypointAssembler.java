package kr.co.yapp._22nd.coffice.ui.place.waypoint;

import kr.co.yapp._22nd.coffice.domain.place.waypoint.Waypoint;
import kr.co.yapp._22nd.coffice.ui.place.CoordinatesResponse;
import org.springframework.stereotype.Component;

@Component
public class WaypointAssembler {

    public WaypointResponse toWaypointResponse(Waypoint waypoint) {
        return new WaypointResponse(
                waypoint.getWaypointId(),
                waypoint.getName(),
                CoordinatesResponse.from(waypoint.getCoordinates())
        );
    }

}
