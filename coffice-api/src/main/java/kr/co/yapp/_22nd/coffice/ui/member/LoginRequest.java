package kr.co.yapp._22nd.coffice.ui.member;

import lombok.Data;

@Data
public class LoginRequest {
    private final String providerType;
    private final String providerUserId;
}
