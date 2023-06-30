package kr.co.yapp._22nd.coffice.domain;

public class LongCursorPageable implements CursorPageable<Long> {
    private final Long lastSeenKey;
    private final Integer pageSize;

    private LongCursorPageable(Long lastSeenKey, Integer pageSize) {
        this.lastSeenKey = lastSeenKey;
        this.pageSize = pageSize;
    }

    public static LongCursorPageable of(
            Long lastSeenKey,
            Integer pageSize
    ) {
        return new LongCursorPageable(lastSeenKey, pageSize);
    }

    public static LongCursorPageable initial(Integer pageSize) {
        return new LongCursorPageable(null, pageSize);
    }

    @Override
    public Long getLastSeenKey() {
        return lastSeenKey;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }
}
