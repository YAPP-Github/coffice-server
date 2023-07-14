package kr.co.yapp._22nd.coffice.ui.member;

import kr.co.yapp._22nd.coffice.domain.MemberResponseVo;
import kr.co.yapp._22nd.coffice.domain.member.Member;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class MemberAssembler {
    public MemberResponse toMemberResponse(Member member) {
        return new MemberResponse(
                member.getMemberId(),
                member.getName(),
                member.getAuthProviders()
                        .stream()
                        .map(AuthProviderResponse::of)
                        .collect(Collectors.toList())
        );
    }

    public MemberResponse toMemberResponse(MemberResponseVo memberResponseVo) {
        return new MemberResponse(
                memberResponseVo.getMemberId(),
                memberResponseVo.getName(),
                memberResponseVo.getAuthProviders()
                        .stream()
                        .map(AuthProviderResponse::of)
                        .collect(Collectors.toList())
        );
    }
}
