package kr.co.yapp._22nd.coffice.domain.place;

public enum CapacityLevel {
    UNKNOWN(Integer.MIN_VALUE, -1),
    LOW(0, 5),
    MEDIUM(6, 14),
    HIGH(15, Integer.MAX_VALUE),
    ;

    private final int from;
    private final int to;

    CapacityLevel(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public static CapacityLevel from(TableCount tableCount) {
        if (tableCount == null || !tableCount.hasValue()) {
            return UNKNOWN;
        }
        if (tableCount.compareTo(TableCount.from(LOW.from)) < 0) {
            return UNKNOWN;
        }
        if (tableCount.compareTo(TableCount.from(MEDIUM.from)) < 0) {
            return LOW;
        }
        if (tableCount.compareTo(TableCount.from(HIGH.from)) < 0) {
            return MEDIUM;
        }
        return HIGH;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }
}
