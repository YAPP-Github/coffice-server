package kr.co.yapp._22nd.coffice.application.login;

import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginApplicationService {
    private final MemberService memberService;

    public Member login(LoginRequestVo loginRequestVo) {
        return memberService.join(
                loginRequestVo.getProviderType(),
                loginRequestVo.getProviderUserId()
        );
    }
}
