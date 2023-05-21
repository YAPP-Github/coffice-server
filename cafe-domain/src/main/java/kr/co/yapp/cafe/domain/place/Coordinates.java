package kr.co.yapp.cafe.domain.place;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Embeddable
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
}
