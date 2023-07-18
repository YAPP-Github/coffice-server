package kr.co.yapp._22nd.coffice.infrastructure.kakao;

import kr.co.yapp._22nd.coffice.domain.DisconnectRequestVo;
import kr.co.yapp._22nd.coffice.domain.DisconnectService;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderDeleteVo;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoDisconnectService implements DisconnectService {
    private final KakaoApiClient kakaoApiClient;

    @Override
    public AuthProviderDeleteVo disconnect(String authProviderUserId, DisconnectRequestVo disconnectRequestVo) {
        kakaoApiClient.disconnect(authProviderUserId);
        return AuthProviderDeleteVo.of(
                AuthProviderType.KAKAO
        );
    }

    @Override
    public boolean supports(DisconnectRequestVo disconnectRequestVo) {
        return disconnectRequestVo.getAuthProviderType() == AuthProviderType.KAKAO;
    }
}
