package kr.co.yapp._22nd.coffice.infrastructure.ncp;

import kr.co.yapp._22nd.coffice.domain.GeoCodingFailedException;
import kr.co.yapp._22nd.coffice.domain.GeoCodingService;
import kr.co.yapp._22nd.coffice.domain.place.Coordinates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class NcpGeoCodingService implements GeoCodingService {
    private final RestTemplate restTemplate;

    @Override
    public Coordinates getCoordinates(String address) {
        URI uri = UriComponentsBuilder.fromHttpUrl("https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode")
                .queryParam("query", URLEncoder.encode(address, StandardCharsets.UTF_8))
                .build(true)
                .toUri();
        var response = restTemplate.getForEntity(uri, Map.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new GeoCodingFailedException("Failed to get coordinates from NCP GeoCoding API");
        }
        var firstAddress = (Map<String, Object>) ((List<Object>) response.getBody().get("addresses")).get(0);
        return Coordinates.of(
                Double.parseDouble((String) firstAddress.get("y")),
                Double.parseDouble((String) firstAddress.get("x"))
        );
    }
}
