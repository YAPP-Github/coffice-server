package kr.co.yapp._22nd.coffice.infrastructure.apple;

import kr.co.yapp._22nd.coffice.domain.LoginRequestVo;
import kr.co.yapp._22nd.coffice.domain.LoginService;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderCreateVo;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;
import org.springframework.stereotype.Service;

@Service
public class AppleLoginService implements LoginService {
    @Override
    public AuthProviderCreateVo login(LoginRequestVo loginRequestVo) {
        String appleUserId = "testAppleUserId";
        return AuthProviderCreateVo.of(
                AuthProviderType.APPLE,
                appleUserId
        );
    }

    @Override
    public boolean supports(LoginRequestVo loginRequestVo) {
        return loginRequestVo.getAuthProviderType() == AuthProviderType.APPLE;
    }
}
