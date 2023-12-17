package kr.co.yapp._22nd.coffice.infrastructure.naver;

import lombok.Data;

@Data
public class NaverGeocodeAddressDto {
    private String roadAddress;
    private String jibunAddress;
    private String englishAddress;
    private double x; // 경도
    private double y; // 위도
    private double distance;
}
