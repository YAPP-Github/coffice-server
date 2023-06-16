package kr.co.yapp.cafe.domain.place;

public class PlaceNotFoundException extends RuntimeException {
    private final Long placeId;

    public PlaceNotFoundException(Long placeId) {
        this.placeId = placeId;
    }

    @Override
    public String getMessage() {
        return "Place not found. placeId: " + placeId;
    }
}
