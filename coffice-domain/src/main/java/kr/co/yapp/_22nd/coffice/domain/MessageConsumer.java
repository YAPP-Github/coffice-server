package kr.co.yapp._22nd.coffice.domain;

public interface MessageConsumer<T> {
    void receive(T message);
}
