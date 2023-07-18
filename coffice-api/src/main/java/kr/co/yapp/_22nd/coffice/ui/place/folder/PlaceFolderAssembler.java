package kr.co.yapp._22nd.coffice.ui.place.folder;

import kr.co.yapp._22nd.coffice.domain.place.Place;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolder;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolderCreateVo;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolderUpdateVo;
import kr.co.yapp._22nd.coffice.domain.place.folder.place.PlaceFolderPlace;
import kr.co.yapp._22nd.coffice.ui.place.PlaceAssembler;
import kr.co.yapp._22nd.coffice.ui.place.PlaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class PlaceFolderAssembler {
    private final PlaceAssembler placeAssembler;

    public PlaceFolderResponse toPlaceFolderResponse(PlaceFolder placeFolder) {
        return new PlaceFolderResponse(
                placeFolder.getPlaceFolderId(),
                placeFolder.getName(),
                placeFolder.getColor().name()
        );
    }

    public PlaceFolderDetailResponse toPlaceFolderDetailResponse(Long memberId, PlaceFolder placeFolder) {
        Stream<Place> placeStream = placeFolder.getPlaceFolderPlaces()
                .stream()
                .map(PlaceFolderPlace::getPlace);
        List<PlaceResponse> placeResponses = placeAssembler.toPlaceResponses(memberId, placeStream).toList();
        return new PlaceFolderDetailResponse(
                placeFolder.getPlaceFolderId(),
                placeFolder.getName(),
                placeResponses
        );
    }

    public PlaceFolderCreateVo toPlaceFolderCreateVo(
            PlaceFolderCreateRequest placeFolderCreateRequest
    ) {
        return new PlaceFolderCreateVo(
                placeFolderCreateRequest.getName(),
                placeFolderCreateRequest.getColor()
        );
    }

    public PlaceFolderUpdateVo toPlaceFolderUpdateVo(PlaceFolderUpdateRequest placeFolderUpdateRequest) {
        return new PlaceFolderUpdateVo(
                placeFolderUpdateRequest.getName(),
                placeFolderUpdateRequest.getColor()
        );
    }
}
