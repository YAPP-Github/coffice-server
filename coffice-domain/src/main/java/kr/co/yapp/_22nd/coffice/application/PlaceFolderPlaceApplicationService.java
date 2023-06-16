package kr.co.yapp._22nd.coffice.application;

import kr.co.yapp._22nd.coffice.domain.place.folder.place.PlaceFolderPlace;
import kr.co.yapp._22nd.coffice.domain.place.folder.place.PlaceFolderPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceFolderPlaceApplicationService {
    private final PlaceFolderPlaceService placeFolderPlaceService;

    public PlaceFolderPlace saveToPlaceFolder(
            Long memberId,
            Long placeFolderId,
            Long placeId
    ) {
        return placeFolderPlaceService.add(memberId, placeFolderId, placeId);
    }

    public void deleteFromPlaceFolder(
            Long memberId,
            Long placeFolderId,
            Long placeId
    ) {
        placeFolderPlaceService.remove(memberId, placeFolderId, placeId);
    }
}
