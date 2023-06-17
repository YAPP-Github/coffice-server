package kr.co.yapp._22nd.coffice.ui.place;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlaceUpdateRequest {
    private String name;
    private Double latitude;
    private Double longitude;
}
