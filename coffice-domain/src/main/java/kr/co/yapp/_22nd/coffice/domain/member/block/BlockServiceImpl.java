package kr.co.yapp._22nd.coffice.domain.member.block;

import kr.co.yapp._22nd.coffice.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BlockServiceImpl implements BlockService {
    private final BlockRepository blockRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Block create(Long memberId, Long blockedMemberId) {
        if (Objects.equals(memberId, blockedMemberId)) {
            throw new BlockMyselfException(memberId);
        }
        return blockRepository.findByMember_memberIdAndBlockedMember_memberIdAndDeletedFalse(memberId, blockedMemberId)
                .orElseGet(() -> blockRepository.save(
                                Block.of(
                                        memberRepository.getReferenceById(memberId),
                                        memberRepository.getReferenceById(blockedMemberId)
                                )
                        )
                );
    }

    @Override
    @Transactional
    public void delete(Long memberId, Long blockedMemberId) {
        blockRepository.findByMember_memberIdAndBlockedMember_memberIdAndDeletedFalse(memberId, blockedMemberId)
                .ifPresent(Block::delete);
    }
}
