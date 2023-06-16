package kr.co.yapp._22nd.coffice.infrastructure.security;

import kr.co.yapp._22nd.coffice.domain.AdminMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

@RequiredArgsConstructor
public class CofficeAdminOidcUserService implements OAuth2UserService<OidcUserRequest, OidcUser> {
    private final OidcUserService oidcUserService = new OidcUserService();
    private final AdminMemberRepository adminMemberRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        var loadedUser = oidcUserService.loadUser(userRequest);
        if (!isInternalUser(loadedUser)) {
            throw new OAuth2AuthenticationException("접근 권한이 없습니다. email: " + loadedUser.getAttribute("email"));
        }
        return loadedUser;
    }

    private boolean isInternalUser(OidcUser oidcUser) {
        String email = oidcUser.getAttribute("email");
        if (email == null) {
            return false;
        }
        return adminMemberRepository.findByEmail(email).isPresent();
    }
}
