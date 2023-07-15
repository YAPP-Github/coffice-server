package kr.co.yapp._22nd.coffice.infrastructure.apple;

import kr.co.yapp._22nd.coffice.domain.LoginRequestVo;
import kr.co.yapp._22nd.coffice.domain.LoginService;
import kr.co.yapp._22nd.coffice.domain.ProviderUserInfo;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderCreateVo;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppleLoginService implements LoginService {
    private final AppleJwtUtils appleJwtUtils;

    @Override
    public AuthProviderCreateVo login(LoginRequestVo loginRequestVo) {
        String appleAccessToken = loginRequestVo.getAuthProviderUserId();
        ProviderUserInfo userInfo = appleJwtUtils.getUserInfo(appleAccessToken);
        String appleUserId = userInfo.getId();
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
