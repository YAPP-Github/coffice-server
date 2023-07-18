package kr.co.yapp._22nd.coffice.domain;

import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderDeleteVo;

public interface DisconnectService {
    AuthProviderDeleteVo disconnect(String authProviderUserId, DisconnectRequestVo disconnectRequestVo);
    boolean supports(DisconnectRequestVo disconnectRequestVo);
}
