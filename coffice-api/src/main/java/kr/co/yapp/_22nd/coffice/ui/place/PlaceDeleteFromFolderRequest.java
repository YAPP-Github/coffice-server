package kr.co.yapp._22nd.coffice.ui.place;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlaceDeleteFromFolderRequest {
    @NotNull
    private Long placeFolderId;
}
