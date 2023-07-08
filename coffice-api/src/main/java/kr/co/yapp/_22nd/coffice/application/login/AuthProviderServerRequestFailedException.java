package kr.co.yapp._22nd.coffice.application.login;

import kr.co.yapp._22nd.coffice.domain.CofficeException;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;

public class AuthProviderServerRequestFailedException extends CofficeException {
    public AuthProviderServerRequestFailedException(AuthProviderType authProviderType) {
        super(authProviderType.name() + " server request failed.");
    }
}
