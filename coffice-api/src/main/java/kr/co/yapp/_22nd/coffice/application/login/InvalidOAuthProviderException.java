package kr.co.yapp._22nd.coffice.application.login;

import kr.co.yapp._22nd.coffice.domain.CofficeException;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;

public class InvalidOAuthProviderException extends CofficeException {
    public InvalidOAuthProviderException(AuthProviderType authProviderType) {
        super("Invalid OAuth Provider Requested. authProviderType: " + authProviderType.name());
    }
}
