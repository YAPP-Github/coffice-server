package kr.co.yapp._22nd.coffice.infrastructure.spring;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JwtAuthenticationProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {
    private static final Pattern PATTERN_AUTHORIZATION_HEADER = Pattern.compile("^[Bb]earer (.*)$");

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return resolveAccessToken(request);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return null;
    }

    private String resolveAccessToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            return null;
        }
        Matcher matcher = PATTERN_AUTHORIZATION_HEADER.matcher(authorization);
        if (!matcher.matches()) {
            return null;
        }
        return matcher.group(1);
    }
}
