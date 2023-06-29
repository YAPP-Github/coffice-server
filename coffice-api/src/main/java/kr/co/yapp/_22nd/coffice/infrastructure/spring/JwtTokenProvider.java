package kr.co.yapp._22nd.coffice.infrastructure.spring;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    static int EXPIRATION_DURATION_IN_YEAR = 100;
    private final Algorithm algorithm;

    @Autowired
    public JwtTokenProvider(@Value("${coffice.jwt.secret}") String secretKey) {
        this.algorithm = Algorithm.HMAC256(secretKey);
    }

    public String generateToken(Long memberId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresAt = now.plusYears(EXPIRATION_DURATION_IN_YEAR);
        return JWT.create()
                .withSubject(memberId.toString())
                .withIssuedAt(java.sql.Timestamp.valueOf(now))
                .withExpiresAt(java.sql.Timestamp.valueOf(expiresAt))
                .sign(algorithm);
    }

    public Long getMemberIdFromToken(String token) {
        DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);
        try {
            return Long.parseLong(decodedJWT.getSubject());
        } catch (NumberFormatException e) {
            throw new BadCredentialsException("Invalid subject: " + decodedJWT.getSubject());
        }
    }

    public boolean isValidToken(String token) {
        try {
            JWT.require(algorithm).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
