package kr.co.yapp._22nd.coffice.application.search;

import kr.co.yapp._22nd.coffice.domain.CofficeDomainEvent;
import kr.co.yapp._22nd.coffice.domain.search.SearchRequestedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchEventHandler implements CofficeDomainEvent {
    private final SearchWordApplicationService searchWordApplicationService;

    @Async
    @EventListener(SearchRequestedEvent.class)
    public void handleSearchRequested(SearchRequestedEvent event) {
        searchWordApplicationService.create(
                event.getMemberId(),
                event.getText()
        );
    }
}
