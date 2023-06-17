package kr.co.yapp._22nd.coffice.domain.place;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Distance implements Comparable<Distance> {
    BigDecimal value;
    DistanceUnit unit;

    private Distance(BigDecimal value, DistanceUnit unit) {
        this.unit = unit;
        this.value = toMeterValue(value, unit);
    }

    private static BigDecimal toMeterValue(BigDecimal value, DistanceUnit unit) {
        switch (unit) {
            case KILOMETER -> {
                return value.multiply(BigDecimal.valueOf(1000));
            }
            case METER -> {
                return value;
            }
            default -> throw new IllegalArgumentException("'unit' is not supported. unit: " + unit);
        }
    }

    public static Distance of(Double value, DistanceUnit unit) {
        return new Distance(BigDecimal.valueOf(value), unit);
    }

    public static Distance of(BigDecimal value, DistanceUnit unit) {
        return new Distance(value, unit);
    }

    @Override
    public int compareTo(Distance o) {
        return toMeterValue(value, unit).compareTo(toMeterValue(o.value, o.unit));
    }

    public Double toKilometerValue() {
        return toMeterValue(value, unit)
                .divide(BigDecimal.valueOf(1000))
                .doubleValue();
    }
}
