package kr.co.yapp._22nd.coffice.domain;

import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderCreateVo;

public interface LoginService {
    AuthProviderCreateVo login(LoginRequestVo loginRequestVo);

    boolean supports(LoginRequestVo loginRequestVo);
}
