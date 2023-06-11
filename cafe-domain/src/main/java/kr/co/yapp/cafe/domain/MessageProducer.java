package kr.co.yapp.cafe.domain;

public interface MessageProducer<T> {
    void send(T message);
}
