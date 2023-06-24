package kr.co.yapp._22nd.coffice.domain.place;

public interface Countable<T, R> extends Comparable<T> {
    R getValue();

    boolean hasValue();

    boolean isPositive();
}
