package kr.co.yapp._22nd.coffice.domain.member;

import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;
import lombok.Value;

@Value(staticConstructor = "of")
public class MemberJoinVo {
    AuthProviderType authProviderType;
    String authProviderUserId;
}
