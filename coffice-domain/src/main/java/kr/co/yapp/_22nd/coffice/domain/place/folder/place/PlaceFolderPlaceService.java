package kr.co.yapp._22nd.coffice.domain.place.folder.place;

import java.util.List;

public interface PlaceFolderPlaceService {
    PlaceFolderPlace add(Long memberId, Long placeFolderId, Long placeId);

    void remove(Long memberId, Long placeFolderId, Long placeId);

    List<PlaceFolderPlace> update(Long memberId, Long placeId, List<Long> placeFolderIds);
}
