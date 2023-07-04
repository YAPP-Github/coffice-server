package kr.co.yapp._22nd.coffice.application;

import kr.co.yapp._22nd.coffice.domain.place.Place;
import kr.co.yapp._22nd.coffice.domain.place.PlaceQueryService;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolder;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolderService;
import kr.co.yapp._22nd.coffice.domain.place.folder.place.PlaceFolderPlace;
import kr.co.yapp._22nd.coffice.domain.place.folder.place.PlaceFolderPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceArchiveApplicationService {
    private final PlaceFolderService placeFolderService;
    private final PlaceQueryService placeQueryService;
    private final PlaceFolderPlaceService placeFolderPlaceService;

    public List<Place> getArchivedPlaces(Long memberId) {
        PlaceFolder defaultPlaceFolder = placeFolderService.getDefaultPlaceFolder(memberId);
        return placeQueryService.findByPlaceFolderId(memberId, defaultPlaceFolder.getPlaceFolderId());
    }

    public Place addArchivedPlace(Long memberId, Long placeId) {
        PlaceFolder defaultPlaceFolder = placeFolderService.getDefaultPlaceFolder(memberId);
        PlaceFolderPlace placeFolderPlace = placeFolderPlaceService.add(memberId, defaultPlaceFolder.getPlaceFolderId(), placeId);
        return placeFolderPlace.getPlace();
    }

    public void removeArchivedPlace(Long memberId, Long placeId) {
        PlaceFolder defaultPlaceFolder = placeFolderService.getDefaultPlaceFolder(memberId);
        placeFolderPlaceService.remove(memberId, defaultPlaceFolder.getPlaceFolderId(), placeId);
    }

    public void removeArchivedPlaces(Long memberId, Collection<Long> placeIds) {
        PlaceFolder defaultPlaceFolder = placeFolderService.getDefaultPlaceFolder(memberId);
        placeFolderPlaceService.removeAll(memberId, defaultPlaceFolder.getPlaceFolderId(), placeIds);
    }

}
