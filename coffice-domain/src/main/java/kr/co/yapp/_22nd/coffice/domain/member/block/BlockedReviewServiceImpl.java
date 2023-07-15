package kr.co.yapp._22nd.coffice.domain.member.block;

import kr.co.yapp._22nd.coffice.domain.member.MemberRepository;
import kr.co.yapp._22nd.coffice.domain.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BlockedReviewServiceImpl implements BlockedReviewService {
    private final BlockedReviewRepository blockedReviewRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public BlockedReview blockReview(Long memberId, Long reviewId) {
        return blockedReviewRepository.findByMember_memberIdAndReview_reviewIdAndDeletedFalse(memberId, reviewId)
                .orElseGet(() -> blockedReviewRepository.save(
                                BlockedReview.of(
                                        memberRepository.getReferenceById(memberId),
                                        reviewRepository.getReferenceById(reviewId)
                                )
                        )
                );
    }

    @Override
    @Transactional
    public void unblockReview(Long memberId, Long reviewId) {
        blockedReviewRepository.findByMember_memberIdAndReview_reviewIdAndDeletedFalse(memberId, reviewId)
                .ifPresent(BlockedReview::delete);
    }
}
