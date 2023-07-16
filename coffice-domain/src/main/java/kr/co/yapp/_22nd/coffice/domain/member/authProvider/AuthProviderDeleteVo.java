package kr.co.yapp._22nd.coffice.domain.member.authProvider;

import lombok.Value;

@Value(staticConstructor = "of")
public class AuthProviderDeleteVo {
    AuthProviderType authProviderType;
}
