package kr.co.yapp._22nd.coffice.domain;

public class LongCursorPageable implements CursorPageable<Long> {
    private final Long lastSeenKey;
    private final int pageSize;

    private LongCursorPageable(Long lastSeenKey, int pageSize) {
        this.lastSeenKey = lastSeenKey;
        this.pageSize = pageSize;
    }

    public static LongCursorPageable of(Long lastSeenKey, int pageSize) {
        return new LongCursorPageable(lastSeenKey, pageSize);
    }

    public static LongCursorPageable initial(int pageSize) {
        return new LongCursorPageable(null, pageSize);
    }

    @Override
    public Long getLastSeenKey() {
        return lastSeenKey;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }
}
