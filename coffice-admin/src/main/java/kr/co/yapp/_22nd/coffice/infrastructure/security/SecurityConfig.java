package kr.co.yapp._22nd.coffice.infrastructure.security;

import kr.co.yapp._22nd.coffice.domain.AdminMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.requestMatchers(
                                        HttpMethod.GET,
                                        "favicon.ico",
                                        "/error",
                                        "/css/**",
                                        "/js/**",
                                        "/images/**",
                                        "/webjars/**"
                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .csrf().disable() // FIXME: csrf 사용해도 POST 요청 403 안나게 개선
                .oauth2Login()
                .and()
                .build();
    }


    @Bean
    public OAuth2UserService<OidcUserRequest, OidcUser> cofficeAdminOidcUserService(
            AdminMemberRepository adminMemberRepository
    ) {
        return new CofficeAdminOidcUserService(adminMemberRepository);
    }
}
