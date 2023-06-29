package kr.co.yapp._22nd.coffice.application.login;

import kr.co.yapp._22nd.coffice.domain.member.*;
import kr.co.yapp._22nd.coffice.infrastructure.spring.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginApplicationService {
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponseVo login(LoginRequestVo loginRequestVo) {
        Member member = findOrCreateMember(loginRequestVo);
        String token = jwtTokenProvider.generateToken(member.getMemberId());
        return LoginResponseVo.of(token, member);
    }

    private Member findOrCreateMember(LoginRequestVo loginRequestVo) {
        try {
            return memberService.getMember(loginRequestVo.getAuthProviderType(), loginRequestVo.getAuthProviderUserId());
        } catch (MemberNotFoundException e) {
            return memberService.join(loginRequestVo.getAuthProviderUserId());
        }
    }
}
