package kr.co.yapp._22nd.coffice.application;

import kr.co.yapp._22nd.coffice.domain.member.block.BlockedReviewService;
import kr.co.yapp._22nd.coffice.domain.place.report.PlaceReport;
import kr.co.yapp._22nd.coffice.domain.place.report.PlaceReportCreateVo;
import kr.co.yapp._22nd.coffice.domain.place.report.PlaceReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportApplicationService {
    private final BlockedReviewService blockedReviewService;
    private final PlaceReportService placeReportService;

    public void reportReview(Long memberId, Long reviewId) {
        blockedReviewService.blockReview(memberId, reviewId);
    }
    public PlaceReport reportPlace(
            Long memberId,
            PlaceReportCreateVo placeReportCreateVo
    ) {
        return placeReportService.create(memberId, placeReportCreateVo);
    }
}
