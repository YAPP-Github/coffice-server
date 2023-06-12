package kr.co.yapp.cafe.domain.place.folder;

import kr.co.yapp.cafe.domain.member.Member;
import kr.co.yapp.cafe.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceFolderServiceImpl implements PlaceFolderService {
    private final PlaceFolderRepository placeFolderRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public PlaceFolder create(
            Long memberId,
            PlaceFolderCreateVo placeFolderCreateVo
    ) {
        validate(memberId, placeFolderCreateVo);
        Member member = memberRepository.getReferenceById(memberId);
        PlaceFolder placeFolder = PlaceFolder.of(member, placeFolderCreateVo);
        return placeFolderRepository.save(placeFolder);
    }

    private void validate(
            Long memberId,
            PlaceFolderCreateVo placeFolderCreateVo
    ) {
        boolean isDuplicated = placeFolderRepository.findByMember_memberIdAndNameContainsAndDeletedFalse(memberId, placeFolderCreateVo.getName()).isPresent();
        if (isDuplicated) {
            throw new PlaceFolderDuplicatedException(memberId, placeFolderCreateVo.getName());
        }
    }

    @Transactional
    @Override
    public PlaceFolder update(
            Long memberId,
            Long placeFolderId,
            PlaceFolderUpdateVo placeFolderUpdateVo
    ) {
        PlaceFolder placeFolder = placeFolderRepository.findByMember_memberIdAndPlaceFolderIdAndDeletedFalse(memberId, placeFolderId)
                .orElseThrow(() -> new PlaceFolderNotFoundException(placeFolderId));
        placeFolder.update(placeFolderUpdateVo);
        return placeFolder;
    }

    @Override
    @Transactional
    public void delete(
            Long memberId,
            Long placeFolderId
    ) {
        placeFolderRepository.findByMember_memberIdAndPlaceFolderIdAndDeletedFalse(memberId, placeFolderId)
                .ifPresent(PlaceFolder::delete);
    }

    @Override
    public List<PlaceFolder> getPlaceFolders(Long memberId) {
        return placeFolderRepository.findByMember_memberIdAndDeletedFalse(memberId);
    }

    @Override
    public PlaceFolder getPlaceFolder(
            Long memberId,
            Long placeFolderId
    ) {
        return placeFolderRepository.findByMember_memberIdAndPlaceFolderIdAndDeletedFalse(memberId, placeFolderId)
                .orElseThrow(() -> new PlaceFolderNotFoundException(placeFolderId));
    }
}
