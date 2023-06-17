package kr.co.yapp._22nd.coffice.domain.place;

public interface PlaceCommandService {
    Place create(PlaceCreateVo placeCreateVo);

    Place update(Long placeId, PlaceUpdateVo placeUpdateVo);
}
