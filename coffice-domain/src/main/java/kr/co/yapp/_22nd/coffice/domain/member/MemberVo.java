package kr.co.yapp._22nd.coffice.domain.member;

import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderVo;
import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class MemberVo {
    Long memberId;
    String name;
    List<AuthProviderVo> authProviders;
}
