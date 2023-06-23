package kr.co.yapp._22nd.coffice.domain.member;

import kr.co.yapp._22nd.coffice.domain.NotFoundException;

public class MemberNotFoundException extends NotFoundException {

    public MemberNotFoundException(Long memberId) {
        super("Member not found. memberId: " + memberId);
    }
}
