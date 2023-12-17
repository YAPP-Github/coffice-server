package kr.co.yapp._22nd.coffice.domain;

import kr.co.yapp._22nd.coffice.infrastructure.naver.NaverPlaceSearchItemDto;
import lombok.Value;

@Value(staticConstructor = "of")
public class NaverPlaceSearchItem {
    String title;
    String link;
    String category;
    String description;
    String telephone;
    String address;
    String roadAddress;
    Double mapx;
    Double mapy;
    boolean isRegistered; // TODO: coffice 앱 내 등록 여부 확인

    public static NaverPlaceSearchItem from(
            NaverPlaceSearchItemDto naverPlaceSearchItemDto
    ) {
        return new NaverPlaceSearchItem(
                naverPlaceSearchItemDto.getTitle().replace("<b>", "").replace("</b>", ""),
                naverPlaceSearchItemDto.getLink(),
                naverPlaceSearchItemDto.getCategory(),
                naverPlaceSearchItemDto.getDescription(),
                naverPlaceSearchItemDto.getTelephone(),
                naverPlaceSearchItemDto.getAddress(),
                naverPlaceSearchItemDto.getRoadAddress(),
                Double.parseDouble(naverPlaceSearchItemDto.getMapx()),
                Double.parseDouble(naverPlaceSearchItemDto.getMapy()),
                false
        );
    }
}
