package kr.co.yapp._22nd.coffice.ui.place;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlaceCreateRequest {
    private String name;
    private Double latitude;
    private Double longitude;
}
