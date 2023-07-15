package kr.co.yapp._22nd.coffice.domain.member.block;

public interface BlockedReviewService {
    BlockedReview blockReview(Long memberId, Long blockedMemberId);

    void unblockReview(Long memberId, Long blockedMemberId);
}
