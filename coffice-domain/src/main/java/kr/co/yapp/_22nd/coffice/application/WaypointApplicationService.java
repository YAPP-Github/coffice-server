package kr.co.yapp._22nd.coffice.application;

import kr.co.yapp._22nd.coffice.domain.place.waypoint.Waypoint;
import kr.co.yapp._22nd.coffice.domain.place.waypoint.WaypointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WaypointApplicationService {
    private final WaypointService waypointService;

    public List<Waypoint> findWaypoints(String name) {
        return waypointService.findWaypointsByName(name);
    }
}
