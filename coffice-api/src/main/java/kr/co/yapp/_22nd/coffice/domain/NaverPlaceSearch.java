package kr.co.yapp._22nd.coffice.domain;

import kr.co.yapp._22nd.coffice.infrastructure.naver.NaverPlaceSearchDto;
import kr.co.yapp._22nd.coffice.infrastructure.naver.NaverPlaceSearchItemDto;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value(staticConstructor = "of")
public class NaverPlaceSearch {
    String lastBuildDate;
    String total;
    String start;
    String display;
    List<NaverPlaceSearchItem> items;

    public static NaverPlaceSearch from(
            NaverPlaceSearchDto naverPlaceSearchDto
    ) {
        return new NaverPlaceSearch(
                naverPlaceSearchDto.getLastBuildDate(),
                naverPlaceSearchDto.getTotal(),
                naverPlaceSearchDto.getStart(),
                naverPlaceSearchDto.getDisplay(),
                naverPlaceSearchDto.getItems()
                        .stream()
                        .map(NaverPlaceSearchItem::from)
                        .collect(Collectors.toList())
        );
    }
}
