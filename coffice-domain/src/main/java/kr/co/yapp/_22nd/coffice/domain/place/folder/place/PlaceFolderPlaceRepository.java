package kr.co.yapp._22nd.coffice.domain.place.folder.place;

import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.place.Place;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PlaceFolderPlaceRepository extends JpaRepository<PlaceFolderPlace, Long> {
    Optional<PlaceFolderPlace> findByMemberAndPlaceFolderAndPlace(Member member, PlaceFolder placeFolder, Place place);

    List<PlaceFolderPlace> findByMemberAndPlaceFolderAndPlace_placeIdIn(Member member, PlaceFolder placeFolder, Collection<Long> placeIds);

    List<PlaceFolderPlace> findByMember_memberIdAndPlaceFolder_placeFolderId(Long memberId, Long placeFolderId);

    void deleteByMember_memberIdAndPlace_placeId(Long memberId, Long placeId);
}
