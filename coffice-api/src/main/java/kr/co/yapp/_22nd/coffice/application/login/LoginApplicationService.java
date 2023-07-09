package kr.co.yapp._22nd.coffice.application.login;

import kr.co.yapp._22nd.coffice.domain.LoginRequestVo;
import kr.co.yapp._22nd.coffice.domain.LoginResponseVo;
import kr.co.yapp._22nd.coffice.domain.LoginService;
import kr.co.yapp._22nd.coffice.domain.UnsupportedLoginTypeException;
import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.member.MemberCommandService;
import kr.co.yapp._22nd.coffice.domain.member.MemberQueryService;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderCreateVo;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderVo;
import kr.co.yapp._22nd.coffice.infrastructure.spring.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginApplicationService {
    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;
    private final JwtTokenProvider jwtTokenProvider;
    private final List<LoginService> loginServices;

    public LoginResponseVo login(LoginRequestVo loginRequestVo) {
        LoginService loginService =resolveLoginService(loginRequestVo);
        AuthProviderCreateVo authProviderCreateVo = loginService.login(loginRequestVo);
        Member member = memberQueryService.getMember(AuthProviderVo.active(authProviderCreateVo))
                .orElseGet(() -> memberCommandService.join(authProviderCreateVo));
        String token = jwtTokenProvider.generateToken(member.getMemberId());
        return LoginResponseVo.of(token, member);
    }

    private LoginService resolveLoginService(LoginRequestVo loginRequestVo) {
        return loginServices.stream()
                .filter(loginService -> loginService.supports(loginRequestVo))
                .findFirst()
                .orElseThrow(() -> new UnsupportedLoginTypeException(loginRequestVo));
    }
}
