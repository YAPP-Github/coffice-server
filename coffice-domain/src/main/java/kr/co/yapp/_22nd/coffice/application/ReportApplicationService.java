package kr.co.yapp._22nd.coffice.application;

import kr.co.yapp._22nd.coffice.domain.member.block.BlockedReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportApplicationService {
    private final BlockedReviewService blockedReviewService;

    public void reportReview(Long memberId, Long reviewId) {
        blockedReviewService.blockReview(memberId, reviewId);
    }
}
