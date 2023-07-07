package kr.co.yapp._22nd.coffice.domain.place.waypoint;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.co.yapp._22nd.coffice.domain.place.Coordinates;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * 지도를 이동하기 위한 기준이 되는 주요 장소
 */
@Entity
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Waypoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long waypointId;
    
    private String name;

    private Coordinates coordinates;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate

    private LocalDateTime updatedAt;
}
