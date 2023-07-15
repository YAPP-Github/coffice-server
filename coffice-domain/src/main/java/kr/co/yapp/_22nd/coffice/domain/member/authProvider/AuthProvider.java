package kr.co.yapp._22nd.coffice.domain.member.authProvider;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@ToString
@Embeddable
@EntityListeners(AuditingEntityListener.class)
public class AuthProvider {
    @Enumerated(EnumType.STRING)
    private AuthProviderType authProviderType;
    private String authProviderUserId;
    @Enumerated(EnumType.STRING)
    private AuthProviderStatus authProviderStatus;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static AuthProvider from(AuthProviderCreateVo authProviderCreateVo) {
        AuthProvider authProvider = new AuthProvider();
        authProvider.authProviderType = authProviderCreateVo.getAuthProviderType();
        authProvider.authProviderUserId = authProviderCreateVo.getAuthProviderUserId();
        authProvider.authProviderStatus = AuthProviderStatus.ACTIVE;
        return authProvider;
    }

    public void delete() {
        this.authProviderStatus = AuthProviderStatus.WITHDRAWAL;
    }
}
