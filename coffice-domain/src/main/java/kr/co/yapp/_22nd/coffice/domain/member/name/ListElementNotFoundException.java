package kr.co.yapp._22nd.coffice.domain.member.name;

import kr.co.yapp._22nd.coffice.domain.NotFoundException;

public class ListElementNotFoundException extends NotFoundException {
    public ListElementNotFoundException() {
        super("Modifier or Subject list is empty or null");
    }
}
