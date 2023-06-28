package kr.co.yapp._22nd.coffice.domain.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class AuthProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authProviderId;
    private String authProviderType;
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
