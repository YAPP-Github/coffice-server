package kr.co.yapp._22nd.coffice.domain.member.block;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlockRepository extends JpaRepository<Block, Long> {
    Optional<Block> findByMember_memberIdAndBlockedMember_memberIdAndDeletedFalse(Long memberId, Long blockedMemberId);
}
