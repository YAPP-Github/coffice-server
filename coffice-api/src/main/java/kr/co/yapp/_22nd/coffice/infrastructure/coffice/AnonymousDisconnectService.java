package kr.co.yapp._22nd.coffice.infrastructure.coffice;

import kr.co.yapp._22nd.coffice.domain.DisconnectRequestVo;
import kr.co.yapp._22nd.coffice.domain.DisconnectService;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderDeleteVo;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnonymousDisconnectService implements DisconnectService {
    @Override
    public AuthProviderDeleteVo disconnect(String authProviderUserId, DisconnectRequestVo disconnectRequestVo) {
        return AuthProviderDeleteVo.of(
                AuthProviderType.ANONYMOUS
        );
    }

    @Override
    public boolean supports(DisconnectRequestVo disconnectRequestVo) {
        return disconnectRequestVo.getAuthProviderType() == AuthProviderType.ANONYMOUS;
    }
}
