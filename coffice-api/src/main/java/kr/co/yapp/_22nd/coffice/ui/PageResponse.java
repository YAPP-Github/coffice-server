package kr.co.yapp._22nd.coffice.ui;

import org.springframework.data.domain.Slice;

public record PageResponse(boolean hasNext) {
    public static PageResponse from(Slice<?> slice) {
        return new PageResponse(slice.hasNext());
    }
}
