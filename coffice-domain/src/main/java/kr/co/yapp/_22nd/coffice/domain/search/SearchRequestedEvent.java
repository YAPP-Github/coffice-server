package kr.co.yapp._22nd.coffice.domain.search;

import kr.co.yapp._22nd.coffice.domain.CofficeDomainEvent;
import lombok.Value;

@Value
public class SearchRequestedEvent implements CofficeDomainEvent {
    Long memberId;
    String text;
}
