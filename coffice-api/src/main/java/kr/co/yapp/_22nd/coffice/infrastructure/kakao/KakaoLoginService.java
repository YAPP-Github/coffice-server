package kr.co.yapp._22nd.coffice.infrastructure.kakao;

import kr.co.yapp._22nd.coffice.domain.LoginRequestVo;
import kr.co.yapp._22nd.coffice.domain.LoginService;
import kr.co.yapp._22nd.coffice.domain.ProviderUserInfo;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderCreateVo;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoLoginService implements LoginService {
    private final KakaoApiClient kakaoApiClient;

    @Override
    public AuthProviderCreateVo login(LoginRequestVo loginRequestVo) {
        if (loginRequestVo.getAuthProviderType() != AuthProviderType.KAKAO) {
            throw new IllegalArgumentException("KakaoLoginService only supports KAKAO. loginRequestVo: " + loginRequestVo);
        }
        String kakaoAccessToken = loginRequestVo.getAuthProviderUserId();
        ProviderUserInfo userInfo = kakaoApiClient.getUserInfo(kakaoAccessToken);
        String kakaoUserId = userInfo.getId();
        return AuthProviderCreateVo.of(AuthProviderType.KAKAO, kakaoUserId);
    }

    @Override
    public boolean supports(LoginRequestVo loginRequestVo) {
        return loginRequestVo.getAuthProviderType() == AuthProviderType.KAKAO;
    }
}
