package kr.co.yapp._22nd.coffice.ui.place.folder;

import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolderColors;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlaceFolderUpdateRequest {
    private String name;
    private PlaceFolderColors color;
}
