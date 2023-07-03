package kr.co.yapp._22nd.coffice.domain.place.folder;

import lombok.Value;

@Value
public class PlaceFolderCreateVo {
    String name;
    PlaceFolderColors color;

    public static PlaceFolderCreateVo defaultFolder() {
        return new PlaceFolderCreateVo(
                "기본 폴더",
                PlaceFolderColors.COLOR_01
        );
    }
}
