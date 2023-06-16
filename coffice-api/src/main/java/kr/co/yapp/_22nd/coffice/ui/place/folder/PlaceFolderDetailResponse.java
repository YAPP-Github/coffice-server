package kr.co.yapp._22nd.coffice.ui.place.folder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.co.yapp._22nd.coffice.ui.place.PlaceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceFolderDetailResponse {
    @NotNull
    private Long placeFolderId;
    @NotBlank
    private String name;
    @NotNull
    private List<PlaceResponse> places;
}
