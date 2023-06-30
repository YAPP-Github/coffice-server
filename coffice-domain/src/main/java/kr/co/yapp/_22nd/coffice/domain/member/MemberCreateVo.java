package kr.co.yapp._22nd.coffice.domain.member;

import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderCreateVo;
import lombok.Value;

@Value(staticConstructor = "of")
public class MemberCreateVo {
    String name;
    AuthProviderCreateVo authProviderCreateVo;
}
