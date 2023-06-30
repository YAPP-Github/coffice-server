package kr.co.yapp._22nd.coffice.ui.member;

import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProvider;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderVo;
import lombok.Data;

@Data
public class AuthProviderResponse {
    private final String authProviderType;
    private final String authProviderUserId;
    private final String authProviderStatus;

    public static AuthProviderResponse of(AuthProvider authProvider) {
        return new AuthProviderResponse(
                authProvider.getAuthProviderType().name(),
                authProvider.getAuthProviderUserId(),
                authProvider.getAuthProviderStatus().name()
        );
    }

    public static AuthProviderResponse of(AuthProviderVo authProvider) {
        return new AuthProviderResponse(
                authProvider.getAuthProviderType().name(),
                authProvider.getAuthProviderUserId(),
                authProvider.getAuthProviderStatus().name()
        );
    }
}
