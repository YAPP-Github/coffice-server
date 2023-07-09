package kr.co.yapp._22nd.coffice.domain.member.authProvider;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Embeddable
public class AuthProvider {
    @Enumerated(EnumType.STRING)
    private AuthProviderType authProviderType;
    private String authProviderUserId;
    @Enumerated(EnumType.STRING)
    private AuthProviderStatus authProviderStatus;

    public static AuthProvider from(AuthProviderCreateVo authProviderCreateVo) {
        AuthProvider authProvider = new AuthProvider();
        authProvider.authProviderType = authProviderCreateVo.getAuthProviderType();
        authProvider.authProviderUserId = authProviderCreateVo.getAuthProviderUserId();
        authProvider.authProviderStatus = AuthProviderStatus.ACTIVE;
        return authProvider;
    }
}
