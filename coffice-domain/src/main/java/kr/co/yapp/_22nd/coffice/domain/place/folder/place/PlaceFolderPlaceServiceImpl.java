package kr.co.yapp._22nd.coffice.domain.place.folder.place;

import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.member.MemberService;
import kr.co.yapp._22nd.coffice.domain.place.Place;
import kr.co.yapp._22nd.coffice.domain.place.PlaceQueryService;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolder;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceFolderPlaceServiceImpl implements PlaceFolderPlaceService {
    private final MemberService memberService;
    private final PlaceQueryService placeQueryService;
    private final PlaceFolderService placeFolderService;
    private final PlaceFolderPlaceRepository placeFolderPlaceRepository;

    @Override
    @Transactional
    public PlaceFolderPlace add(Long memberId, Long placeFolderId, Long placeId) {
        Member member = memberService.getMember(memberId);
        PlaceFolder placeFolder = placeFolderService.getPlaceFolder(memberId, placeFolderId);
        Place place = placeQueryService.getPlace(placeId);

        return placeFolderPlaceRepository.findByMemberAndPlaceFolderAndPlace(member, placeFolder, place)
                .orElseGet(() -> placeFolderPlaceRepository.save(
                        PlaceFolderPlace.of(member, placeFolder, place)
                ));
    }

    @Override
    @Transactional
    public void remove(Long memberId, Long placeFolderId, Long placeId) {
        Member member = memberService.getMember(memberId);
        PlaceFolder placeFolder = placeFolderService.getPlaceFolder(memberId, placeFolderId);
        Place place = placeQueryService.getPlace(placeId);

        placeFolderPlaceRepository.findByMemberAndPlaceFolderAndPlace(member, placeFolder, place)
                .ifPresent(placeFolderPlaceRepository::delete);
    }
}
