package kr.co.yapp._22nd.coffice.ui.member;

import kr.co.yapp._22nd.coffice.application.login.LoginResponseVo;
import kr.co.yapp._22nd.coffice.domain.member.Member;
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
}
