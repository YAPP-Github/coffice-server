package kr.co.yapp._22nd.coffice.domain.member;

import lombok.Value;

@Value(staticConstructor = "of")

public class MemberUpdateVo {
    String name;
    MemberStatus status;
    AuthProviderType authProviderType;
    AuthProvider activeAuthProvider;
}
