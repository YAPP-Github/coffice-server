package kr.co.yapp._22nd.coffice.infrastructure.naver;

import kr.co.yapp._22nd.coffice.domain.NaverPlaceSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class NaverApiClient {
    private final WebClient naverOpenAPIClient;
    private final WebClient naverAPIClient;
    private final String X_Naver_Client_id;
    private final String X_Naver_Client_Secret;
    private final String X_NCP_APIGW_API_KEY_ID;
    private final String X_NCP_APIGW_API_KEY;

    @Autowired
    public NaverApiClient (
            @Value("${coffice.naver.x_naver_client_id}") String X_Naver_Client_id,
            @Value("${coffice.naver.x_naver_client_secret}") String X_Naver_Client_Secret,
            @Value("${coffice.naver.X_NCP_APIGW_API_KEY_ID}") String X_NCP_APIGW_API_KEY_ID,
            @Value("${coffice.naver.X_NCP_APIGW_API_KEY}") String X_NCP_APIGW_API_KEY,
            WebClient naverOpenAPIClient,
            WebClient naverAPIClient
    ) {
        this.naverOpenAPIClient = naverOpenAPIClient;
        this.naverAPIClient = naverAPIClient;
        this.X_Naver_Client_id = X_Naver_Client_id;
        this.X_Naver_Client_Secret = X_Naver_Client_Secret;
        this.X_NCP_APIGW_API_KEY_ID = X_NCP_APIGW_API_KEY_ID;
        this.X_NCP_APIGW_API_KEY = X_NCP_APIGW_API_KEY;
    }

    /* TODO: NaverUserMeDto.getId(), 4xx, 5xx 예외처리 */
    public NaverPlaceSearch getPlaces(String name) {
        NaverPlaceSearchDto naverPlaceSearchDto = naverOpenAPIClient.get()
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

    public NaverGeocodeAddressDto getGeocode(String address) {
        NaverGeocodeDto naverGeocodeDto = naverAPIClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/geocode")
                        .queryParam("query", address)
                        .build())
                .header("X-NCP-APIGW-API-KEY-ID", X_NCP_APIGW_API_KEY_ID)
                .header("X-NCP-APIGW-API-KEY", X_NCP_APIGW_API_KEY)
                .retrieve()
                .bodyToMono(NaverGeocodeDto.class)
                .block();
        return naverGeocodeDto.getAddresses().stream().findFirst().get();
    }
}
