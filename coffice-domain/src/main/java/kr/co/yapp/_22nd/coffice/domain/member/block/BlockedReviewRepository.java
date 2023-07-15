package kr.co.yapp._22nd.coffice.domain.member.block;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlockedReviewRepository extends JpaRepository<BlockedReview, Long> {
    Optional<BlockedReview> findByMember_memberIdAndReview_reviewIdAndDeletedFalse(Long memberId, Long reviewId);
}
