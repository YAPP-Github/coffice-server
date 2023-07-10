package kr.co.yapp._22nd.coffice.application.login;

import kr.co.yapp._22nd.coffice.domain.*;
import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.member.MemberCommandService;
import kr.co.yapp._22nd.coffice.domain.member.MemberQueryService;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderCreateVo;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderStatus;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;
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
        LoginService loginService = resolveLoginService(loginRequestVo);
        AuthProviderCreateVo authProviderCreateVo = loginService.login(loginRequestVo);
        Member member = memberQueryService.getMember(AuthProviderVo.active(authProviderCreateVo))
                .orElseGet(() -> memberCommandService.join(authProviderCreateVo));
        String token = jwtTokenProvider.generateToken(member.getMemberId());
        return LoginResponseVo.of(token, member);
    }

    public LoginResponseVo connectAuthProvider(Long memberId, LoginRequestVo loginRequestVo) {
        validateAuthProviderConnectRequest(memberId, loginRequestVo);
        LoginService loginService = resolveLoginService(loginRequestVo);
        AuthProviderCreateVo authProviderCreateVo = loginService.login(loginRequestVo);
        return LoginResponseVo.of(null, memberCommandService.connectAuthProvider(memberId, authProviderCreateVo));
    }

    private LoginService resolveLoginService(LoginRequestVo loginRequestVo) {
        return loginServices.stream()
                .filter(loginService -> loginService.supports(loginRequestVo))
                .findFirst()
                .orElseThrow(() -> new UnsupportedLoginTypeException(loginRequestVo));
    }

    private void validateAuthProviderConnectRequest(Long memberId, LoginRequestVo loginRequestVo) {
        Member member = memberQueryService.getMember(memberId);
        AuthProviderType requestedAuthProviderType = loginRequestVo.getAuthProviderType();
        if (requestedAuthProviderType == AuthProviderType.ANONYMOUS) {
            throw new BadRequestException("잘못된 인증 제공자 연결 요청입니다. AuthProviderType: " + requestedAuthProviderType);
        }
        member.getAuthProviders().stream()
                .filter(authProvider -> authProvider.getAuthProviderType() == requestedAuthProviderType
                    && authProvider.getAuthProviderStatus() == AuthProviderStatus.ACTIVE)
                .findFirst()
                .ifPresent(authProvider -> {
                    throw new BadRequestException("이미 인증 제공자가 연결되어 있습니다. AuthProvider: " + authProvider);
                });
    }
}
