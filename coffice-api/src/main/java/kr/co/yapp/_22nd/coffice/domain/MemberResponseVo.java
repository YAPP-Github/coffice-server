package kr.co.yapp._22nd.coffice.domain;

import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderVo;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class MemberResponseVo {
    Long memberId;
    String name;
    List<AuthProviderVo> authProviders;

    public static MemberResponseVo of(
            Member member
    ) {
        return new MemberResponseVo(
                member.getMemberId(),
                member.getName(),
                member.getAuthProviders()
                        .stream()
                        .map(AuthProviderVo::of)
                        .collect(Collectors.toList())
        );
    }
}
