package kr.co.yapp._22nd.coffice.domain.member;

import kr.co.yapp._22nd.coffice.domain.NotFoundException;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;

public class MemberNotFoundException extends NotFoundException {

    public MemberNotFoundException(Long memberId) {
        super("Member not found. memberId: " + memberId);
    }

    public MemberNotFoundException(AuthProviderType authProviderType, String authProviderUserId) {
        super("Member not found. authProviderType: " + authProviderType + ", authProviderUserId: " + authProviderUserId);
    }
}
