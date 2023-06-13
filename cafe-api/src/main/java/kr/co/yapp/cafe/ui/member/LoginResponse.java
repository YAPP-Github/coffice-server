package kr.co.yapp.cafe.ui.member;

import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;
    private MemberResponse member;
}
