package kr.co.yapp._22nd.coffice.application;

import kr.co.yapp._22nd.coffice.domain.NaverPlaceSearch;
import kr.co.yapp._22nd.coffice.domain.NaverPlaceSearchItem;
import kr.co.yapp._22nd.coffice.infrastructure.naver.NaverApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NaverPlaceSearchService {
    private final NaverApiClient naverApiClient;
    public List<NaverPlaceSearchItem> search(String name) {
        return filter(naverApiClient.getPlaces(name));
    }

    private List<NaverPlaceSearchItem> filter(NaverPlaceSearch naverPlaceSearch) {
        return naverPlaceSearch.getItems()
                .stream()
                .filter(it -> it.getCategory().contains("카페") || it.getCategory().contains("디저트"))
                .collect(Collectors.toList());
    }
}
