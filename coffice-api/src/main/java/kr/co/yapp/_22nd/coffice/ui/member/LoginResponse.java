package kr.co.yapp._22nd.coffice.ui.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginResponse {
    @NotBlank
    private String accessToken;
    @NotNull
    private MemberResponse member;
}
