package kr.co.yapp._22nd.coffice.infrastructure.kakao;

import kr.co.yapp._22nd.coffice.domain.ProviderUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class KakaoApiClient {
    private final WebClient kakaoWebClient;

    /* TODO: kakaoUserMeDto.getId(), 4xx, 5xx 예외처리 */
    ProviderUserInfo getUserInfo(String kakaoAccessToken) {
        KakaoUserMeDto kakaoUserMeDto = kakaoWebClient.get()
                .uri("/v2/user/me")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + kakaoAccessToken)
                .retrieve()
                .bodyToMono(KakaoUserMeDto.class)
                .block();
        return ProviderUserInfo.of(
                kakaoUserMeDto.getId()
        );
    }
}
