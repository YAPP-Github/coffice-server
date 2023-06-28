package kr.co.yapp._22nd.coffice.infrastructure.spring;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.Map;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.GET,
                                "/hello",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/members/login").permitAll()
                        .anyRequest().authenticated()
                )
                .logout().disable()
                .cors().configurationSource(configurationSource())
                .and()
                .csrf().disable()
                .addFilterAt(generateAuthenticationFilter(), AbstractPreAuthenticatedProcessingFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    handlerExceptionResolver.resolveException(request, response, null, authException);
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    handlerExceptionResolver.resolveException(request, response, null, accessDeniedException);
                })
                .and()
                .build();
    }

    @Bean
    public AbstractPreAuthenticatedProcessingFilter generateAuthenticationFilter() throws Exception {
        JwtAuthenticationProcessingFilter authenticationFilter = new JwtAuthenticationProcessingFilter();
        authenticationFilter.setAuthenticationManager(jwtTokenAuthenticationManager());
        return authenticationFilter;
    }

    @Bean
    public AuthenticationManager jwtTokenAuthenticationManager() {
        // TODO: jwt 검증, 회원번호 조회
        return (authentication -> {
            Object principal = authentication.getPrincipal(); // accessToken
            if (principal instanceof String && jwtTokenProvider.validateToken((String) principal)) {
                Long memberId = jwtTokenProvider.getMemberIdFromToken((String) principal);
                PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(memberId, principal);
                token.setAuthenticated(true);
                return token;
            }
            throw new BadCredentialsException("Invalid accessToken");
        });
    }

    // FIXME: '*' 제외해야함
    @Bean
    public CorsConfigurationSource configurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.setCorsConfigurations(Map.of("/**", corsConfiguration));
        return source;
    }

}
