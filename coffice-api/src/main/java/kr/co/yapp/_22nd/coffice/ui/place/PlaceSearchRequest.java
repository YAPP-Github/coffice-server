package kr.co.yapp._22nd.coffice.ui.place;

import lombok.Data;

@Data
public class PlaceSearchRequest {
    private String searchText;
    private Double latitude;
    private Double longitude;
    private Double distance;
    private Integer pageSize;
    private Integer pageNumber;
}
