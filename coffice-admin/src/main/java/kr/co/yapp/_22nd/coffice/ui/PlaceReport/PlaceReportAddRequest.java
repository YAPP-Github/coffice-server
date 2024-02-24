package kr.co.yapp._22nd.coffice.ui.PlaceReport;

import lombok.Data;

import java.util.List;

@Data
public class PlaceReportAddRequest {
    private String name;
    private Double latitude;
    private Double longitude;
    private String streetAddress;
    private String landAddress;
    private String postalCode;
    private String phoneNumber;
    private String homepageUrl;
    private Integer electricOutletCount;
    private Integer seatCount;
    private Integer tableCount;
    private Integer communalTableCount;
    private List<String> imageUrls;
    private List<String> foodTypes;
    private List<String> restroomTypes;
    private List<String> drinkTypes;
}
