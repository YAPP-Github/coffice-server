package kr.co.yapp._22nd.coffice.domain.member.authProvider;

import lombok.Value;

@Value(staticConstructor = "of")
public class AuthProviderVo {
    AuthProviderType authProviderType;
    String authProviderUserId;
    AuthProviderStatus authProviderStatus;

    public static AuthProviderVo of(AuthProvider authProvider) {
        return new AuthProviderVo(
                authProvider.getAuthProviderType(),
                authProvider.getAuthProviderUserId(),
                authProvider.getAuthProviderStatus()
        );
    }
}
