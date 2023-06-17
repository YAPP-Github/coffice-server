package kr.co.yapp._22nd.coffice.domain.place;

public enum DistanceUnit {
    METER,
    KILOMETER,
    ;

    public static DistanceUnit defaults() {
        return METER;
    }
}
