package kr.co.yapp._22nd.coffice.ui.review;

import jakarta.validation.constraints.NotNull;
import kr.co.yapp._22nd.coffice.domain.place.ElectricOutletLevel;
import kr.co.yapp._22nd.coffice.domain.place.NoiseLevel;
import kr.co.yapp._22nd.coffice.domain.place.WifiLevel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ReviewCreateRequest {
    @NotNull
    private ElectricOutletLevel electricOutletLevel;
    @NotNull
    private WifiLevel wifiLevel;
    @NotNull
    private NoiseLevel noiseLevel;
    @Length(max = 200)
    private String content;
}
