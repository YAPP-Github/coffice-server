package kr.co.yapp.cafe.domain.place;

public enum CoordinateSystem {
    WGS84,
    ;

    public static CoordinateSystem getDefault() {
        return WGS84;
    }
}
