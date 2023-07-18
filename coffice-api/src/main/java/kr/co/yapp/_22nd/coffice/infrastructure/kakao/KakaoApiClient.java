package kr.co.yapp._22nd.coffice.infrastructure.kakao;

import kr.co.yapp._22nd.coffice.domain.BadRequestException;
import kr.co.yapp._22nd.coffice.domain.ProviderUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class KakaoApiClient {
    @Value("${coffice.kakao.admin-key}")
    private String kakaoAdminKey;
    private final WebClient kakaoWebClient;

    /* TODO: kakaoUserMeDto.getId(), 4xx, 5xx 예외처리 */
    ProviderUserInfo getUserInfo(String kakaoAccessToken) {
        KakaoUserMeDto kakaoUserMeDto = kakaoWebClient.get()
                .uri("/v2/user/me")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + kakaoAccessToken)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(KakaoUserMeDto.class)
                .block();
        return ProviderUserInfo.of(
                kakaoUserMeDto.getId()
        );
    }

    /* TODO: 예외처리 리팩토링 */
    void disconnect(String kakaoUserId) {
        kakaoWebClient.post()
                .uri("/v1/user/unlink")
                .header(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoAdminKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(BodyInserters.fromFormData("target_id_type", "user_id")
                        .with("target_id", kakaoUserId))
                .retrieve()
                .onStatus(
                        status -> status.equals(HttpStatus.BAD_REQUEST),
                        clientResponse -> {throw new BadRequestException("잘못된 요청값 입니다.");})
                .onStatus(
                        status -> status.equals(HttpStatus.UNAUTHORIZED),
                        clientResponse -> {throw new BadRequestException("토큰이 유효하지 않습니다.");}
                )
                .bodyToMono(Void.class)
                .block();
    }
}
