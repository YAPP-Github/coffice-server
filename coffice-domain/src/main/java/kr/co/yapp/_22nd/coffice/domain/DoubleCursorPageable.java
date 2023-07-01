package kr.co.yapp._22nd.coffice.domain;

public class DoubleCursorPageable implements CursorPageable<Double> {
    private final Double lastSeenKey;
    private final int pageSize;

    private DoubleCursorPageable(Double lastSeenKey, int pageSize) {
        this.lastSeenKey = lastSeenKey;
        this.pageSize = pageSize;
    }

    public static DoubleCursorPageable of(Double lastSeenKey, int pageSize) {
        return new DoubleCursorPageable(lastSeenKey, pageSize);
    }

    public static DoubleCursorPageable initial(int pageSize) {
        return new DoubleCursorPageable(null, pageSize);
    }

    @Override
    public Double getLastSeenKey() {
        return lastSeenKey;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }
}
