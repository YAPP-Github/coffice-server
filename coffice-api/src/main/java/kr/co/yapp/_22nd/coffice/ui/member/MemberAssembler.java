package kr.co.yapp._22nd.coffice.ui.member;

import kr.co.yapp._22nd.coffice.domain.member.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberAssembler {
    public MemberResponse toMemberResponse(Member member) {
        return new MemberResponse(
                member.getMemberId(),
                member.getName(),
                member.getAuthProviders()
        );
    }
}
