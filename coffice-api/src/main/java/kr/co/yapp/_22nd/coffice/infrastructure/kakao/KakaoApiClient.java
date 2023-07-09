package kr.co.yapp._22nd.coffice.infrastructure.kakao;

import kr.co.yapp._22nd.coffice.domain.ProviderUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KakaoApiClient {
    private final RestTemplate kakaoResetTemplate;

    /**
     * 카카오 사용자 정보 가져오기
     *
     * @see "https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#req-user-info"
     */
    ProviderUserInfo getUserInfo(String kakaoAccessToken) {
        URI url = UriComponentsBuilder.fromHttpUrl("https://kapi.kakao.com/v2/user/me")
                .build()
                .toUri();

        try {
            MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();
            headerMap.put("Authorization", List.of("Bearer " + kakaoAccessToken));
            ResponseEntity<KakaoUserMeDto> responseEntity = kakaoResetTemplate.exchange(
                    new RequestEntity<>(headerMap, HttpMethod.GET, url),
                    KakaoUserMeDto.class
            );
            KakaoUserMeDto kakaoUserMeDto = responseEntity.getBody();
            Assert.notNull(kakaoUserMeDto, "'kakaoUserMeDto' must not be null");
            return ProviderUserInfo.of(
                    kakaoUserMeDto.getId()
            );
        } catch (RestClientException e) {
            throw new KakaoApiFailedException("카카오 사용자 정보 가져오기 API 호출에 실패했습니다.", e);
        }
    }
}
