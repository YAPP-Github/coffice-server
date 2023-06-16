package kr.co.yapp._22nd.coffice.ui.place;

import kr.co.yapp._22nd.coffice.ui.member.MemberResponse;
import kr.co.yapp._22nd.coffice.ui.place.folder.PlaceFolderResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceFolderPlaceResponse {
    private MemberResponse member;
    private PlaceFolderResponse placeFolder;
    private PlaceResponse place;
}
