package kr.co.yapp._22nd.coffice.domain.place.folder.place;

public interface PlaceFolderPlaceService {
    PlaceFolderPlace add(Long memberId, Long placeFolderId, Long placeId);

    void remove(Long memberId, Long placeFolderId, Long placeId);
}
