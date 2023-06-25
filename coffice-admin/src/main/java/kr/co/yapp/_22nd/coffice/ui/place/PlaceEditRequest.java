package kr.co.yapp._22nd.coffice.ui.place;

import lombok.Data;

@Data
public class PlaceEditRequest {
    private String name;
    private Double latitude;
    private Double longitude;
    private String streetAddress;
    private String landAddress;
    private String postalCode;
    private String phoneNumber;
    private Integer electricOutletCount;
    private Integer seatCount;
    private Integer tableCount;
    private Integer communalTableCount;
}
