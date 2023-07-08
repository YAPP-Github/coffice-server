package kr.co.yapp._22nd.coffice.application.login;

import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.member.MemberCommandService;
import kr.co.yapp._22nd.coffice.domain.member.MemberJoinVo;
import kr.co.yapp._22nd.coffice.domain.member.MemberQueryService;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderStatus;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderVo;
import kr.co.yapp._22nd.coffice.infrastructure.spring.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginApplicationService {
    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponseVo anonymousLogin(String uuid) {
        return loginOrJoin(AuthProviderType.ANONYMOUS, uuid);
    }

    public LoginResponseVo oAuthLogin(OAuthLoginRequestVo oAuthLoginRequestVo) {
        AuthProviderType authProviderType = oAuthLoginRequestVo.getAuthProviderType();
        String oAuthProviderUserId = getOAuthProviderUserId(oAuthLoginRequestVo);
        return loginOrJoin(authProviderType, oAuthProviderUserId);
    }

    private LoginResponseVo loginOrJoin(AuthProviderType authProviderType, String authProviderUserId) {
        Member member = memberQueryService.getMember(
                        AuthProviderVo.of(authProviderType, authProviderUserId, AuthProviderStatus.ACTIVE)
                )
                .orElseGet(() -> memberCommandService.join(
                        MemberJoinVo.of(authProviderType, authProviderUserId)
                ));
        String token = jwtTokenProvider.generateToken(member.getMemberId());
        return LoginResponseVo.of(token, member);
    }

    private String getOAuthProviderUserId(OAuthLoginRequestVo oAuthLoginRequestVo)  {
        return switch (oAuthLoginRequestVo.getAuthProviderType()) {
            case KAKAO -> getKakaoUserId(oAuthLoginRequestVo.getAuthProviderIdToken());
            case APPLE ->
                /* TODO: apple userId 조회 */
                    "appleUserId";
            default -> throw new InvalidOAuthProviderException(oAuthLoginRequestVo.getAuthProviderType());
        };
    }

    private String getKakaoUserId(String accessToken){
        String baseUrl = "https://kapi.kakao.com";
        WebClient webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return webClient.get()
                .uri("/v1/user/access_token_info")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        response -> Mono.error(new AuthProviderServerRequestFailedException(AuthProviderType.KAKAO)))
                .onStatus(HttpStatusCode::is5xxServerError,
                        response -> Mono.error(new AuthProviderServerRequestFailedException(AuthProviderType.KAKAO)))
                .bodyToMono(Map.class)
                .map(body -> body.get("id").toString())
                .filter(id -> id != null && !id.isEmpty())
                .switchIfEmpty(Mono.error(new AuthProviderServerRequestFailedException(AuthProviderType.KAKAO)))
                .block();
    }
}
