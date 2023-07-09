package kr.co.yapp._22nd.coffice.domain.review;

import jakarta.annotation.Nullable;
import kr.co.yapp._22nd.coffice.domain.place.ElectricOutletLevel;
import kr.co.yapp._22nd.coffice.domain.place.NoiseLevel;
import kr.co.yapp._22nd.coffice.domain.place.WifiLevel;
import lombok.Value;

@Value(staticConstructor = "of")
public class ReviewUpdateVo {
    @Nullable
    ElectricOutletLevel electricOutletLevel;
    @Nullable
    WifiLevel wifiLevel;
    @Nullable
    NoiseLevel noiseLevel;
    @Nullable
    String content;
}
