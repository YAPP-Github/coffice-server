package kr.co.yapp._22nd.coffice.domain.place.waypoint;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaypointRepository extends JpaRepository<Waypoint, Long> {
    List<Waypoint> findByNameContains(String name);
}
