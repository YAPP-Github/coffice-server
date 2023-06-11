package kr.co.yapp.cafe.domain;

public interface MessageConsumer<T> {
    void receive(T message);
}
