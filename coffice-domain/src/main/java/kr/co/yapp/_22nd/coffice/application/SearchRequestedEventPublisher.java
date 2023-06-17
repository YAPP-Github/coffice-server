package kr.co.yapp._22nd.coffice.application;

import kr.co.yapp._22nd.coffice.domain.CofficeDomainEvent;
import kr.co.yapp._22nd.coffice.domain.CofficeDomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchRequestedEventPublisher implements CofficeDomainEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(CofficeDomainEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
