package kr.co.yapp._22nd.coffice.application.login;

import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.member.MemberCommandService;
import kr.co.yapp._22nd.coffice.domain.member.MemberNotFoundException;
import kr.co.yapp._22nd.coffice.domain.member.MemberQueryService;
import kr.co.yapp._22nd.coffice.infrastructure.spring.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginApplicationService {
    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponseVo login(LoginRequestVo loginRequestVo) {
        Member member = findOrCreateMember(loginRequestVo);
        String token = jwtTokenProvider.generateToken(member.getMemberId());
        return LoginResponseVo.of(token, member);
    }

    private Member findOrCreateMember(LoginRequestVo loginRequestVo) {
        try {
            return memberQueryService.getMember(loginRequestVo.getAuthProviderType(), loginRequestVo.getAuthProviderUserId());
        } catch (MemberNotFoundException e) {
            return memberCommandService.join(loginRequestVo.getAuthProviderUserId());
        }
    }
}
