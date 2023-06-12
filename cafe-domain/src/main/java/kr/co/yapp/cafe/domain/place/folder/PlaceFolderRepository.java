package kr.co.yapp.cafe.domain.place.folder;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaceFolderRepository extends JpaRepository<PlaceFolder, Long> {
    List<PlaceFolder> findByMember_memberIdAndDeletedFalse(Long memberId);

    Optional<PlaceFolder> findByMember_memberIdAndPlaceFolderIdAndDeletedFalse(Long memberId, Long placeFolderId);

    Optional<PlaceFolder> findByMember_memberIdAndNameContainsAndDeletedFalse(Long memberId, String name);
}
