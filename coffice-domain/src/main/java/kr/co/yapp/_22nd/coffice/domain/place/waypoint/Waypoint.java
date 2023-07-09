package kr.co.yapp._22nd.coffice.domain.place.waypoint;

import jakarta.persistence.*;
import kr.co.yapp._22nd.coffice.domain.place.Coordinates;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 지도를 이동하기 위한 기준이 되는 주요 장소
 */
@Entity
@Getter
@ToString
@EqualsAndHashCode(of = "waypointId")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
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
