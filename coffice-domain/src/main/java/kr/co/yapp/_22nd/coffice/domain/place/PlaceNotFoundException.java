package kr.co.yapp._22nd.coffice.domain.place;

import kr.co.yapp._22nd.coffice.domain.NotFoundException;

public class PlaceNotFoundException extends NotFoundException {

    public PlaceNotFoundException(Long placeId) {
        super("Place not found. placeId: " + placeId);
    }
}
