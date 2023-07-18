package kr.co.yapp._22nd.coffice.domain.place.folder.place;

import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.member.MemberQueryService;
import kr.co.yapp._22nd.coffice.domain.place.Place;
import kr.co.yapp._22nd.coffice.domain.place.PlaceQueryService;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolder;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceFolderPlaceServiceImpl implements PlaceFolderPlaceService {
    private final MemberQueryService memberQueryService;
    private final PlaceQueryService placeQueryService;
    private final PlaceFolderService placeFolderService;
    private final PlaceFolderPlaceRepository placeFolderPlaceRepository;

    @Override
    @Transactional
    public List<PlaceFolderPlace> update(
            Long memberId,
            Long placeId,
            List<Long> placeFolderIds
    ) {
        Member member = memberQueryService.getMember(memberId);
        List<PlaceFolder> placeFolders = placeFolderService.getPlaceFolders(memberId, placeFolderIds);
        Place place = placeQueryService.getPlace(placeId);

        placeFolderPlaceRepository.deleteByMember_memberIdAndPlace_placeId(memberId, placeId);
        List<PlaceFolderPlace> placeFolderPlaces = placeFolders.stream()
                .map(it -> PlaceFolderPlace.of(member, it, place))
                .collect(Collectors.toList());
        return placeFolderPlaceRepository.saveAll(placeFolderPlaces);
    }

    @Override
    @Transactional
    public PlaceFolderPlace add(
            Long memberId,
            Long placeFolderId,
            Long placeId
    ) {
        Member member = memberQueryService.getMember(memberId);
        PlaceFolder placeFolder = placeFolderService.getPlaceFolder(memberId, placeFolderId);
        Place place = placeQueryService.getPlace(placeId);

        return placeFolderPlaceRepository.findByMemberAndPlaceFolderAndPlace(member, placeFolder, place)
                .orElseGet(() -> placeFolderPlaceRepository.save(
                        PlaceFolderPlace.of(member, placeFolder, place)
                ));
    }

    @Override
    @Transactional
    public void remove(
            Long memberId,
            Long placeFolderId,
            Long placeId
    ) {
        Member member = memberQueryService.getMember(memberId);
        PlaceFolder placeFolder = placeFolderService.getPlaceFolder(memberId, placeFolderId);
        Place place = placeQueryService.getPlace(placeId);

        placeFolderPlaceRepository.findByMemberAndPlaceFolderAndPlace(member, placeFolder, place)
                .ifPresent(placeFolderPlaceRepository::delete);
    }

    @Override
    @Transactional
    public void removeAll(
            Long memberId,
            Long placeFolderId,
            Collection<Long> placeIds
    ) {
        Member member = memberQueryService.getMember(memberId);
        PlaceFolder placeFolder = placeFolderService.getPlaceFolder(memberId, placeFolderId);

        List<PlaceFolderPlace> placeFolderPlaces = placeFolderPlaceRepository.findByMemberAndPlaceFolderAndPlace_placeIdIn(member, placeFolder, placeIds);
        placeFolderPlaceRepository.deleteAll(placeFolderPlaces);
    }

    @Override
    public boolean exists(
            Long memberId,
            Long placeFolderId,
            Long placeId
    ) {
        return placeFolderPlaceRepository.existsByMember_memberIdAndPlaceFolder_placeFolderIdAndPlace_placeId(memberId, placeFolderId, placeId);
    }
}
