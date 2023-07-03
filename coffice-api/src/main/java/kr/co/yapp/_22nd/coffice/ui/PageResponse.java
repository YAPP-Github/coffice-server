package kr.co.yapp._22nd.coffice.ui;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

public record PageResponse(boolean hasNext) {

    public static PageResponse unpaged() {
        return new PageResponse(false);
    }

    public static PageResponse from(Slice<?> slice) {
        return new PageResponse(slice.hasNext());
    }

    public static PageResponse from(Page<?> page) {
        return new PageResponse(page.hasNext());
    }
}
