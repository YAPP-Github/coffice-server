package kr.co.yapp.cafe.domain.place.folder;

public class PlaceFolderNotFoundException extends RuntimeException {
    private final Long placeFolderId;

    public PlaceFolderNotFoundException(Long placeFolderId) {
        this.placeFolderId = placeFolderId;
    }

    @Override
    public String getMessage() {
        return "PlaceFolder not found. placeFolderId: " + placeFolderId;
    }
}
