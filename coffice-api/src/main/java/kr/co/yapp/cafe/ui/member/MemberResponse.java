package kr.co.yapp.cafe.ui.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MemberResponse {
    @NotNull
    private Long memberId;
    @NotBlank
    private String name;
}
