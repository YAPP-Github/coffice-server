package kr.co.yapp._22nd.coffice.ui.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AnonymousLoginRequest {
    @NotBlank
    private String uuid;
}
