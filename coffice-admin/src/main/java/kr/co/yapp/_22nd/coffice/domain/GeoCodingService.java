package kr.co.yapp._22nd.coffice.domain;

import kr.co.yapp._22nd.coffice.domain.place.Coordinates;

public interface GeoCodingService {
    Coordinates getCoordinates(String address);
}
