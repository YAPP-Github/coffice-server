package kr.co.yapp._22nd.coffice.domain.place;

import java.math.BigDecimal;

public enum ElectricOutletLevel {
    UNKNOWN,
    FEW,
    SEVERAL,
    MANY,
    ;

    public static ElectricOutletLevel of(
            ElectricOutletCount electricOutletCount,
            SeatCount seatCount
    ) {
        if (electricOutletCount == null || seatCount == null || !electricOutletCount.hasValue() || !seatCount.hasValue() || seatCount.isZero()) {
            return UNKNOWN;
        }
        BigDecimal ratio = BigDecimal.valueOf(electricOutletCount.getValue() / (double) seatCount.getValue());
        if (ratio.compareTo(BigDecimal.valueOf(0.5)) < 0) {
            return FEW;
        }
        if (ratio.compareTo(BigDecimal.valueOf(0.8)) < 0) {
            return SEVERAL;
        }
        return MANY;
    }
}
