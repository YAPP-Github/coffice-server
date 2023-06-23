package kr.co.yapp._22nd.coffice.domain.place.folder;

import kr.co.yapp._22nd.coffice.domain.NotFoundException;

public class PlaceFolderNotFoundException extends NotFoundException {
    public PlaceFolderNotFoundException(Long placeFolderId) {
        super("PlaceFolder not found. placeFolderId: " + placeFolderId);
    }
}
