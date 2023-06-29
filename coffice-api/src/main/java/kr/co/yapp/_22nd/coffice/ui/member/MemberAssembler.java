package kr.co.yapp._22nd.coffice.ui.member;

import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderVo;
import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.member.MemberVo;
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
                        .map(it -> AuthProviderResponse.of(it))
                        .collect(Collectors.toList())
        );
    }

    public MemberResponse toMemberResponse(MemberVo member) {
        return new MemberResponse(
                member.getMemberId(),
                member.getName(),
                member.getAuthProviders()
                        .stream()
                        .map(it -> AuthProviderResponse.of(it))
                        .collect(Collectors.toList())
        );
    }

    public MemberVo toMemberVo(Member member) {
        return MemberVo.of(
                member.getMemberId(),
                member.getName(),
                member.getAuthProviders()
                        .stream()
                        .map(it -> AuthProviderVo.of(it))
                        .collect(Collectors.toList())
        );
    }
}
