package kr.co.yapp._22nd.coffice.infrastructure.naver;

import kr.co.yapp._22nd.coffice.domain.NaverPlaceSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class NaverApiClient {
    private final WebClient naverWebClient;
    //@Value("${coffice.naver.X_Naver_Client_id}")
    private String X_Naver_Client_id;
    //@Value("${coffice.naver.X_Naver_Client_Secret}")
    private String X_Naver_Client_Secret;

    @Autowired
    public NaverApiClient (
            @Value("${coffice.naver.x_naver_client_id}") String X_Naver_Client_id,
            @Value("${coffice.naver.x_naver_client_secret}") String X_Naver_Client_Secret,
            WebClient naverWebClient
    ) {
        this.naverWebClient = naverWebClient;
        this.X_Naver_Client_id = X_Naver_Client_id;
        this.X_Naver_Client_Secret = X_Naver_Client_Secret;
    }

    /* TODO: NaverUserMeDto.getId(), 4xx, 5xx 예외처리 */
    public NaverPlaceSearch getPlaces(String name) {
        NaverPlaceSearchDto naverPlaceSearchDto = naverWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/search/local")
                        .queryParam("query", name)
                        .queryParam("display", 100)
                        .build())
                .header("X-Naver-Client-Id", X_Naver_Client_id)
                .header("X-Naver-Client-Secret", X_Naver_Client_Secret)
                .retrieve()
                .bodyToMono(NaverPlaceSearchDto.class)
                .block();
        return NaverPlaceSearch.from(naverPlaceSearchDto);
    }
}
