package kr.co.yapp._22nd.coffice.domain;

public interface MessageProducer<T> {
    void send(T message);
}
