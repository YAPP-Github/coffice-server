package kr.co.yapp._22nd.coffice.ui.place;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PlaceToPlaceFolderMapRequest {
    @NotNull
    private List<Long> placeFolderIds;
}
