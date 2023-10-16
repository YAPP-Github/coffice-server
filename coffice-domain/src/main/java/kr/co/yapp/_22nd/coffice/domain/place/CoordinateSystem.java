package kr.co.yapp._22nd.coffice.domain.place;

public enum CoordinateSystem {
    WGS84,
    KATEC,
    ;

    public static CoordinateSystem getDefault() {
        return WGS84;
    }
}
