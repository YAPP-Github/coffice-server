package kr.co.yapp._22nd.coffice.infrastructure.security;

import kr.co.yapp._22nd.coffice.domain.AdminMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

@Configuration
public class SecurityConfig {
    @Bean
    public OAuth2UserService<OidcUserRequest, OidcUser> cofficeAdminOidcUserService(
            AdminMemberRepository adminMemberRepository
    ) {
        return new CofficeAdminOidcUserService(adminMemberRepository);
    }
}
