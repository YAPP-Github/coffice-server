package kr.co.yapp._22nd.coffice.ui.member;

import jakarta.validation.constraints.NotBlank;
import kr.co.yapp._22nd.coffice.application.login.ValidAuthProviderType;
import lombok.Data;

@Data
public class OAuthLoginRequest {
    @ValidAuthProviderType
    private String authProviderType;

    @NotBlank
    private String authProviderIdToken;
}
