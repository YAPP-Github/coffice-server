package kr.co.yapp._22nd.coffice.ui.member;

import kr.co.yapp._22nd.coffice.domain.LoginRequestVo;
import kr.co.yapp._22nd.coffice.domain.LoginResponseVo;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginAssembler {
    private final MemberAssembler memberAssembler;

    public LoginResponse toLoginResponse(LoginResponseVo loginResponseVo) {
        return new LoginResponse(
                loginResponseVo.getAccessToken(),
                memberAssembler.toMemberResponse(loginResponseVo.getMember())
        );
    }

    public LoginRequestVo toLoginRequestVo(LoginRequest loginRequest) {
        return LoginRequestVo.of(
                AuthProviderType.valueOf(loginRequest.getAuthProviderType()),
                loginRequest.getAuthProviderUserId()
        );
    }
}
