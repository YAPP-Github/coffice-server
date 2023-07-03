package kr.co.yapp._22nd.coffice.ui.place.waypoint;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kr.co.yapp._22nd.coffice.application.WaypointApplicationService;
import kr.co.yapp._22nd.coffice.infrastructure.springdoc.SpringdocConfig;
import kr.co.yapp._22nd.coffice.ui.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SecurityRequirement(name = SpringdocConfig.SECURITY_SCHEME_NAME)
@RequestMapping("/api/v1/waypoints")
@RestController
@RequiredArgsConstructor
public class WaypointController {
    private final WaypointApplicationService waypointApplicationService;
    private final WaypointAssembler waypointAssembler;

    @GetMapping
    public ApiResponse<List<WaypointResponse>> getWaypoints(
            @RequestParam(required = false) String name
    ) {
        return ApiResponse.success(
                waypointApplicationService.findWaypoints(name)
                        .stream()
                        .map(waypointAssembler::toWaypointResponse)
                        .toList()
        );
    }
}
