package kr.co.yapp._22nd.coffice.ui.member;

import jakarta.validation.constraints.NotBlank;
import kr.co.yapp._22nd.coffice.application.login.ValidAuthProviderType;
import lombok.Data;

@Data
public class LoginRequest {
    @ValidAuthProviderType
    private final String authProviderType;
    @NotBlank
    private final String authProviderUserId;
}
