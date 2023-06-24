package kr.co.yapp._22nd.coffice.ui.member;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.co.yapp._22nd.coffice.domain.member.AuthProviderType;
import kr.co.yapp._22nd.coffice.domain.member.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberResponse {
    @NotNull
    private Long memberId;
    @NotBlank
    private String name;
    private MemberStatus status;
    private AuthProviderType authProviderType;
}
