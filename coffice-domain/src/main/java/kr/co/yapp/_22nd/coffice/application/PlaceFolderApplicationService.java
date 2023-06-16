package kr.co.yapp._22nd.coffice.application;

import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolder;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolderCreateVo;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolderService;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolderUpdateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceFolderApplicationService {
    private final PlaceFolderService placeFolderService;

    public List<PlaceFolder> getPlaceFolders(Long memberId) {
        return placeFolderService.getPlaceFolders(memberId);
    }

    public PlaceFolder getPlaceFolder(Long memberId, Long placeFolderId) {
        return placeFolderService.getPlaceFolder(memberId, placeFolderId);
    }

    public PlaceFolder create(
            Long memberId,
            PlaceFolderCreateVo placeFolderCreateVo
    ) {
        return placeFolderService.create(memberId, placeFolderCreateVo);
    }

    public PlaceFolder update(
            Long memberId,
            Long placeFolderId,
            PlaceFolderUpdateVo placeFolderUpdateVo
    ) {
        return placeFolderService.update(memberId, placeFolderId, placeFolderUpdateVo);
    }

    public void delete(
            Long memberId,
            Long placeFolderId
    ) {
        placeFolderService.delete(memberId, placeFolderId);
    }
}
