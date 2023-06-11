package kr.co.yapp.cafe.domain.place.folder;

import java.util.List;

public interface PlaceFolderService {
    PlaceFolder create(Long memberId, PlaceFolderCreateVo placeFolderCreateVo);

    PlaceFolder update(Long memberId, Long placeFolderId, PlaceFolderUpdateVo placeFolderUpdateVo);

    List<PlaceFolder> getPlaceFolders(Long memberId);

    PlaceFolder getPlaceFolder(Long memberId, Long placeFolderId);
}
