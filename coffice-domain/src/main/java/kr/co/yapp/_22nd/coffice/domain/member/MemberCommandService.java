package kr.co.yapp._22nd.coffice.domain.member;

import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderCreateVo;

public interface MemberCommandService {
    Member join(AuthProviderCreateVo authProviderCreateVo);
}
