package kr.co.yapp._22nd.coffice.application.login;

import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderVo;
import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.member.MemberVo;
import lombok.Value;

import java.util.stream.Collectors;

@Value
public class LoginResponseVo {
    String accessToken;
    MemberVo member;

    public static LoginResponseVo of(
            String accessToken,
            Member member
    ) {
        return new LoginResponseVo(
                accessToken,
                MemberVo.of(
                        member.getMemberId(),
                        member.getName(),
                        member.getAuthProviders()
                                .stream()
                                .map(AuthProviderVo::of)
                                .collect(Collectors.toList())
                )
        );
    }
}
