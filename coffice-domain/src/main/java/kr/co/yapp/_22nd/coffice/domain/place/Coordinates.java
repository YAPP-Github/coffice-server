package kr.co.yapp._22nd.coffice.domain.place;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.locationtech.proj4j.*;

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
        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        CRSFactory crsFactory = new CRSFactory();
        CoordinateReferenceSystem katechCRS = crsFactory.createFromName("EPSG:5179");
        CoordinateReferenceSystem wgs84CRS = crsFactory.createFromName("EPSG:4326");

        CoordinateTransform transform = ctFactory.createTransform(katechCRS, wgs84CRS);

        ProjCoordinate katechCoordinate = new ProjCoordinate(mapx, mapy);
        ProjCoordinate wgs84Coordinate = new ProjCoordinate();

        // 변환 실행
        transform.transform(katechCoordinate, wgs84Coordinate);
        return Coordinates.of(
                wgs84Coordinate.y,
                wgs84Coordinate.x
        );
    }
}
