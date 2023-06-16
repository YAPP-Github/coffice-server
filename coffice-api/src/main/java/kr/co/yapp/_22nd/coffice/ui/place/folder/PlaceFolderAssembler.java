package kr.co.yapp._22nd.coffice.ui.place.folder;

import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolder;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolderCreateVo;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolderUpdateVo;
import kr.co.yapp._22nd.coffice.domain.place.folder.place.PlaceFolderPlace;
import kr.co.yapp._22nd.coffice.ui.place.PlaceAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlaceFolderAssembler {
    private final PlaceAssembler placeAssembler;

    public PlaceFolderResponse toPlaceFolderResponse(PlaceFolder placeFolder) {
        return new PlaceFolderResponse(
                placeFolder.getPlaceFolderId(),
                placeFolder.getName()
        );
    }

    public PlaceFolderDetailResponse toPlaceFolderDetailResponse(PlaceFolder placeFolder) {
        return new PlaceFolderDetailResponse(
                placeFolder.getPlaceFolderId(),
                placeFolder.getName(),
                placeFolder.getPlaceFolderPlaces()
                        .stream()
                        .map(PlaceFolderPlace::getPlace)
                        .map(placeAssembler::toPlaceResponse)
                        .collect(Collectors.toList())
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
