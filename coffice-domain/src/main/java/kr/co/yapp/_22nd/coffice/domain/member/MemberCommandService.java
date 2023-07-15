package kr.co.yapp._22nd.coffice.domain.member;

import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderCreateVo;
import kr.co.yapp._22nd.coffice.domain.member.name.MemberName;

public interface MemberCommandService {
    Member join(AuthProviderCreateVo authProviderCreateVo);

    Member connect(Long memberId, AuthProviderCreateVo authProviderCreateVo);

    Member updateName(Long memberId, MemberName memberName);
}
