package kr.co.yapp._22nd.coffice.domain.place.folder;

public class PlaceFolderDuplicatedException extends RuntimeException {
    private final Long memberId;
    private final String name;

    public PlaceFolderDuplicatedException(Long memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    @Override
    public String getMessage() {
        return "placeFolder already exists. memberId: " + memberId + ", name: " + name;
    }
}
