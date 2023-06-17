package kr.co.yapp._22nd.coffice.ui.place;

import jakarta.validation.constraints.NotNull;
import kr.co.yapp._22nd.coffice.domain.place.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatesResponse {
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;

    public static CoordinatesResponse from(Coordinates coordinates) {
        return new CoordinatesResponse(
                coordinates.getLatitude(),
                coordinates.getLongitude()
        );
    }
}
