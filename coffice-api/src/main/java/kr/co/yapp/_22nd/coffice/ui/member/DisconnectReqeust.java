package kr.co.yapp._22nd.coffice.ui.member;

import jakarta.validation.constraints.NotNull;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;
import lombok.Data;

@Data
public class DisconnectReqeust {
    @NotNull
    private AuthProviderType authProviderType;
}
