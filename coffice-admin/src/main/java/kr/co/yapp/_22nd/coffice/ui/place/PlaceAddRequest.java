package kr.co.yapp._22nd.coffice.ui.place;

import lombok.Data;

@Data
public class PlaceAddRequest {
    private String name;
    private Double latitude;
    private Double longitude;
    private String streetAddress;
    private String landAddress;
    private String postalCode;
    private Integer seatCount;
    private Integer electricOutletCount;
    private Integer communalTableCount;
}
