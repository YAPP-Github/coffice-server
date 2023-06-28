package kr.co.yapp._22nd.coffice.application.login;

import lombok.Value;

@Value(staticConstructor = "of")
public class LoginRequestVo {
    String providerType;
    String providerUserId;
}
