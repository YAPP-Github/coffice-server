package kr.co.yapp._22nd.coffice.infrastructure.apple;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.yapp._22nd.coffice.domain.ProviderUserInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Map;

@Component
public class AppleJwtUtils {
    private final WebClient appleWebClient;
    private final Base64.Decoder decoder;
    private final ObjectMapper objectMapper;

    public AppleJwtUtils(WebClient appleWebClient) {
        this.appleWebClient = appleWebClient;
        this.decoder = Base64.getUrlDecoder();
        this.objectMapper = new ObjectMapper();
    }

    /* TODO: 예외처리 리팩토링 */
    ProviderUserInfo getUserInfo(String identityToken) {
            ApplePublicKeyResponse applePublicKeyResponse = getAllAppleAuthPublicKeys();
            ApplePublicKeyResponse.Key matchedKey = getMatchedKeyByTokenHeader(identityToken, applePublicKeyResponse);
            RSAPublicKey rsaPublicKey = generateRSAPublicKey(matchedKey);
            DecodedJWT decodedJWT = verifyAndDecodeIdentityToken(identityToken, rsaPublicKey);
            String sub = decodedJWT.getClaims().get("sub").asString();
            return ProviderUserInfo.of(
                    sub
            );
    }

    private ApplePublicKeyResponse getAllAppleAuthPublicKeys() {
        return appleWebClient.get()
                .uri("/auth/keys")
                .retrieve()
                .bodyToMono(ApplePublicKeyResponse.class)
                .block();
    }

    private ApplePublicKeyResponse.Key getMatchedKeyByTokenHeader(
            String identityToken,
            ApplePublicKeyResponse applePublicKeyResponse
    ) {
        String tokenHeader = getTokenHeader(identityToken);
        Map<String, String> decodedTokenHeader = getDecodedTokenHeader(tokenHeader);
        return applePublicKeyResponse.getMatchedKey(decodedTokenHeader.get("kid"), decodedTokenHeader.get("alg"));
    }

    private String getTokenHeader(String identityToken) {
        String[] tokenParts = identityToken.split("\\.");
        if (tokenParts.length < 2) {
            throw new IllegalArgumentException("잘못된 토큰입니다.");
        }
        return tokenParts[0];
    }

    private Map<String, String> getDecodedTokenHeader(String tokenHeader) {
        String decodedHeaderString = new String(decoder.decode(tokenHeader));
        try {
            return objectMapper.readValue(decodedHeaderString, Map.class);
        } catch(Exception e) {
            throw new IllegalArgumentException("잘못된 토큰 헤더 입니다. error: ", e);
        }
    }

    private RSAPublicKey generateRSAPublicKey(ApplePublicKeyResponse.Key key) {
        try{
            BigInteger n = new BigInteger(1,  decoder.decode(key.getN()));
            BigInteger e = new BigInteger(1, decoder.decode(key.getE()));
            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(n, e);
            return (RSAPublicKey) KeyFactory.getInstance(key.getKty()).generatePublic(publicKeySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("잘못된 Key Type 알고리즘 입니다. kty: " + key.getKty());
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("잘못된 KeySpec 입니다. " + e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private DecodedJWT verifyAndDecodeIdentityToken(String identityToken, RSAPublicKey rsaPublicKey) {
        return JWT.require(Algorithm.RSA256(rsaPublicKey, null)).build().verify(identityToken);
    }
}