package kr.co.yapp._22nd.coffice.domain.place.waypoint;

import java.util.List;

public interface WaypointService {
    List<Waypoint> findWaypointsByName(String name);
}
