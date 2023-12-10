package kr.co.yapp._22nd.coffice.infrastructure.naver;

import lombok.Data;

@Data
public class NaverPlaceSearchItemDto {
    private String title;
    private String link;
    private String category;
    private String description;
    private String telephone;
    private String address;
    private String roadAddress;
    private String mapx;
    private String mapy;
}
