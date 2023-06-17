package kr.co.yapp._22nd.coffice.domain.place.folder.place;

import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.place.Place;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceFolderPlaceRepository extends JpaRepository<PlaceFolderPlace, Long> {
    Optional<PlaceFolderPlace> findByMemberAndPlaceFolderAndPlace(Member member, PlaceFolder placeFolder, Place place);

    void deleteByMember_memberIdAndPlace_placeId(Long memberId, Long placeId);
}
