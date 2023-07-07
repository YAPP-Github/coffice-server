package kr.co.yapp._22nd.coffice.domain.place.waypoint;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WaypointServiceImpl implements WaypointService {
    private final WaypointRepository waypointRepository;

    @Override
    public List<Waypoint> findWaypointsByName(String name) {
        if (!StringUtils.hasText(name)) {
            return waypointRepository.findAll();
        }
        return waypointRepository.findByNameContains(name);
    }
}
