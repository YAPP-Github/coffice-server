package kr.co.yapp._22nd.coffice.ui.place;

import kr.co.yapp._22nd.coffice.domain.place.folder.place.PlaceFolderPlace;
import kr.co.yapp._22nd.coffice.ui.member.MemberAssembler;
import kr.co.yapp._22nd.coffice.ui.place.folder.PlaceFolderAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaceFolderPlaceAssembler {
    private final MemberAssembler memberAssembler;
    private final PlaceFolderAssembler placeFolderAssembler;
    private final PlaceAssembler placeAssembler;

    public PlaceFolderPlaceResponse toPlaceFolderPlaceResponse(PlaceFolderPlace placeFolderPlace) {
        return new PlaceFolderPlaceResponse(
                memberAssembler.toMemberResponse(placeFolderPlace.getMember()),
                placeFolderAssembler.toPlaceFolderResponse(placeFolderPlace.getPlaceFolder()),
                placeAssembler.toPlaceResponse(placeFolderPlace.getPlace())
        );
    }
}
