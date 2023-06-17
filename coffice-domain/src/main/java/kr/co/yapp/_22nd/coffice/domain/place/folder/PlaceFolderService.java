package kr.co.yapp._22nd.coffice.domain.place.folder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlaceFolderService {
    PlaceFolder create(Long memberId, PlaceFolderCreateVo placeFolderCreateVo);

    PlaceFolder update(Long memberId, Long placeFolderId, PlaceFolderUpdateVo placeFolderUpdateVo);

    void delete(Long memberId, Long placeFolderId);

    List<PlaceFolder> getPlaceFolders(Long memberId);

    List<PlaceFolder> getPlaceFolders(Long memberId, List<Long> placeFolderIds);

    PlaceFolder getPlaceFolder(Long memberId, Long placeFolderId);

    Page<PlaceFolder> findAll(Pageable pageable);

    PlaceFolder getPlaceFolder(Long placeFolderId);
}
