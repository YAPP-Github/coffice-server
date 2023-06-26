package kr.co.yapp._22nd.coffice.application;

import kr.co.yapp._22nd.coffice.domain.member.block.BlockService;
import kr.co.yapp._22nd.coffice.domain.review.Review;
import kr.co.yapp._22nd.coffice.domain.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportApplicationService {
    private final ReviewService reviewService;
    private final BlockService blockService;

    public void reportReview(Long memberId, Long reviewId) {
        Review review = reviewService.getByReviewId(reviewId);
        blockService.create(memberId, review.getMember().getMemberId());
    }
}
