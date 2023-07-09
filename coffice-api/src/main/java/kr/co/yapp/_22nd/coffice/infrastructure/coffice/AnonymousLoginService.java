package kr.co.yapp._22nd.coffice.infrastructure.coffice;

import kr.co.yapp._22nd.coffice.domain.LoginRequestVo;
import kr.co.yapp._22nd.coffice.domain.LoginService;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderCreateVo;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;
import org.springframework.stereotype.Service;

@Service
public class AnonymousLoginService implements LoginService {
    @Override
    public AuthProviderCreateVo login(LoginRequestVo loginRequestVo) {
        return AuthProviderCreateVo.of(
                AuthProviderType.ANONYMOUS,
                loginRequestVo.getAuthProviderUserId()
        );
    }

    @Override
    public boolean supports(LoginRequestVo loginRequestVo) {
        return loginRequestVo.getAuthProviderType() == AuthProviderType.ANONYMOUS;
    }
}
