package kr.co.yapp.cafe.domain.place;

public enum CoordinateSystem {
    WGS84,
    ;

    static CoordinateSystem getDefault() {
        return WGS84;
    }
}
