package kr.co.yapp._22nd.coffice.application;

import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.member.MemberCommandService;
import kr.co.yapp._22nd.coffice.domain.member.name.MemberName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberApplicationService {
    private final MemberCommandService memberCommandService;

    public Member updateMemberName(
            Long memberId,
            MemberName memberName
    ) {
        return memberCommandService.updateName(memberId, memberName);
    }
}
