package kr.co.yapp._22nd.coffice.ui.place.folder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolderColors;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class PlaceFolderUpdateRequest {
    @NotBlank
    @Length(max = 20)
    private String name;
    @NotNull
    private PlaceFolderColors color;
}
