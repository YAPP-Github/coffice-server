package kr.co.yapp._22nd.coffice.domain.place;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Embeddable
@Getter
@ToString
@EqualsAndHashCode
public class Coordinates {
    /**
     * 위도
     */
    private final Double latitude;
    /**
     * 경도
     */
    private final Double longitude;
    /**
     * 좌표계
     */
    @Enumerated(EnumType.STRING)
    private final CoordinateSystem coordinateSystem;

    private Coordinates(
            Double latitude,
            Double longitude,
            CoordinateSystem coordinateSystem
    ) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.coordinateSystem = coordinateSystem;
    }

    protected Coordinates() {
        this(null, null, null);
    }

    public static Coordinates of(
            Double latitude,
            Double longitude
    ) {
        return new Coordinates(latitude, longitude, CoordinateSystem.getDefault());
    }

    public static Coordinates of(
            Double latitude,
            Double longitude,
            CoordinateSystem coordinateSystem
    ) {
        return new Coordinates(latitude, longitude, coordinateSystem);
    }

    /* TODO : 좌표계 변환 KATEC -> WGS84 */
    public static Coordinates convertFromKATEC(
            Double mapy,
            Double mapx
    ) {
        return Coordinates.of(
                mapy,
                mapx
        );
    }
}
