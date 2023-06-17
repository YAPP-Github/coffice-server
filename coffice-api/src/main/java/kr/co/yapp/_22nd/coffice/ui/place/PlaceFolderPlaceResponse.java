package kr.co.yapp._22nd.coffice.ui.place;

import kr.co.yapp._22nd.coffice.ui.place.folder.PlaceFolderResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceFolderPlaceResponse {
    private PlaceFolderResponse placeFolder;
    private PlaceResponse place;
}
