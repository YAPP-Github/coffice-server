package kr.co.yapp._22nd.coffice.infrastructure.apple;

import kr.co.yapp._22nd.coffice.domain.DisconnectRequestVo;
import kr.co.yapp._22nd.coffice.domain.DisconnectService;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderDeleteVo;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppleDisconnectService implements DisconnectService {
    @Override
    public AuthProviderDeleteVo disconnect(String authProviderUserId, DisconnectRequestVo disconnectRequestVo) {
        /* TODO: Apple 탈퇴 API 호출 (block() (동기 요청)) */
        return AuthProviderDeleteVo.of(
                AuthProviderType.APPLE
        );
    }

    @Override
    public boolean supports(DisconnectRequestVo disconnectRequestVo) {
        return disconnectRequestVo.getAuthProviderType() == AuthProviderType.APPLE;
    }
}
