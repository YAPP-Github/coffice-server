package kr.co.yapp._22nd.coffice.infrastructure.naver;

import lombok.Data;

import java.util.List;

@Data
public class NaverPlaceSearchDto {
    private String lastBuildDate;
    private String total;
    private String start;
    private String display;
    private List<NaverPlaceSearchItemDto> items;
}
