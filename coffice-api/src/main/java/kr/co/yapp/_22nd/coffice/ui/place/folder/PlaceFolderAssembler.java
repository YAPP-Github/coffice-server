package kr.co.yapp._22nd.coffice.ui.place.folder;

import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolder;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolderCreateVo;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolderUpdateVo;
import org.springframework.stereotype.Component;

@Component
public class PlaceFolderAssembler {
    public PlaceFolderResponse toPlaceFolderResponse(PlaceFolder placeFolder) {
        return new PlaceFolderResponse(
                placeFolder.getPlaceFolderId(),
                placeFolder.getName()
        );
    }

    public PlaceFolderCreateVo toPlaceFolderCreateVo(
            PlaceFolderCreateRequest placeFolderCreateRequest
    ) {
        return new PlaceFolderCreateVo(
                placeFolderCreateRequest.getName()
        );
    }

    public PlaceFolderUpdateVo toPlaceFolderUpdateVo(PlaceFolderUpdateRequest placeFolderUpdateRequest) {
        return new PlaceFolderUpdateVo(
                placeFolderUpdateRequest.getName()
        );
    }
}
