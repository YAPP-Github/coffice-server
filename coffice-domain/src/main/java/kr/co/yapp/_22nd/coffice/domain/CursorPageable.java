package kr.co.yapp._22nd.coffice.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface CursorPageable<T> {
    T getLastSeenKey();

    Integer getPageSize();

    default boolean isInitial() {
        return getLastSeenKey() == null;
    }

    default Pageable toPageable() {
        return PageRequest.of(0, getPageSize());
    }
}
