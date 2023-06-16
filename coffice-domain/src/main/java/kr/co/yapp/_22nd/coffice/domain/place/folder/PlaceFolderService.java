package kr.co.yapp._22nd.coffice.domain.place.folder;

import java.util.List;

public interface PlaceFolderService {
    PlaceFolder create(Long memberId, PlaceFolderCreateVo placeFolderCreateVo);

    PlaceFolder update(Long memberId, Long placeFolderId, PlaceFolderUpdateVo placeFolderUpdateVo);

    void delete(Long memberId, Long placeFolderId);

    List<PlaceFolder> getPlaceFolders(Long memberId);

    PlaceFolder getPlaceFolder(Long memberId, Long placeFolderId);
}
