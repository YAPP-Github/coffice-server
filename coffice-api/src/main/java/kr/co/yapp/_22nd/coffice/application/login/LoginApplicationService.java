package kr.co.yapp._22nd.coffice.application.login;

import kr.co.yapp._22nd.coffice.domain.member.*;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderStatus;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderVo;
import kr.co.yapp._22nd.coffice.infrastructure.spring.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginApplicationService {
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponseVo login(LoginRequestVo loginRequestVo) {
        Member member = memberService.getMember(
                AuthProviderVo.of(
                        loginRequestVo.getAuthProviderType(),
                        loginRequestVo.getAuthProviderUserId(),
                        AuthProviderStatus.ACTIVE
                        )
                )
                .orElseGet(() -> memberService.join(loginRequestVo.getAuthProviderUserId()));
        String token = jwtTokenProvider.generateToken(member.getMemberId());
        return LoginResponseVo.of(token, member);
    }
}
