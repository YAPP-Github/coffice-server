package kr.co.yapp._22nd.coffice.ui.place;

import kr.co.yapp._22nd.coffice.domain.place.Coordinates;
import kr.co.yapp._22nd.coffice.domain.place.Distance;
import kr.co.yapp._22nd.coffice.domain.place.DistanceUnit;
import kr.co.yapp._22nd.coffice.domain.place.PlaceSearchRequestVo;
import org.springframework.stereotype.Component;

@Component
public class PlaceSearchAssembler {
    public PlaceSearchRequestVo toPlaceSearchRequestVo(
            PlaceSearchRequest placeSearchRequest
    ) {
        return PlaceSearchRequestVo.of(
                placeSearchRequest.getSearchText(),
                Coordinates.of(
                        placeSearchRequest.getLatitude(),
                        placeSearchRequest.getLongitude()
                ),
                Distance.of(
                        placeSearchRequest.getDistance(),
                        DistanceUnit.METER
                ),
                placeSearchRequest.getOpen(),
                placeSearchRequest.getHasCommunalTable(),
                placeSearchRequest.getCapacityLevels()
        );
    }
}
