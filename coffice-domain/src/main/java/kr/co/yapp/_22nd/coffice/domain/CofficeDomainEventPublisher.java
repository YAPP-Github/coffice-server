package kr.co.yapp._22nd.coffice.domain;

public interface CofficeDomainEventPublisher {
    void publish(CofficeDomainEvent event);
}
