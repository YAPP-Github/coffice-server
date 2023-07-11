package kr.co.yapp._22nd.coffice.domain;

import kr.co.yapp._22nd.coffice.domain.member.Member;
import lombok.Value;


@Value
public class LoginResponseVo {
    String accessToken;
    MemberResponseVo member;

    public static LoginResponseVo of(
            String accessToken,
            Member member
    ) {
        return new LoginResponseVo(
                accessToken,
                MemberResponseVo.of(member)
        );
    }
}
