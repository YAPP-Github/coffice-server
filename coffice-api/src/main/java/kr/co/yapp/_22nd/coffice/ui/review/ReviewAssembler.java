package kr.co.yapp._22nd.coffice.ui.review;

import kr.co.yapp._22nd.coffice.domain.review.Review;
import kr.co.yapp._22nd.coffice.domain.review.ReviewCreateVo;
import kr.co.yapp._22nd.coffice.domain.review.ReviewUpdateVo;
import kr.co.yapp._22nd.coffice.ui.DateTimeAssembler;
import kr.co.yapp._22nd.coffice.ui.member.MemberAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewAssembler {
    private final MemberAssembler memberAssembler;
    private final DateTimeAssembler dateTimeAssembler;

    public ReviewResponse toReviewResponse(Review review) {
        return new ReviewResponse(
                review.getReviewId(),
                memberAssembler.toMemberResponse(review.getMember()),
                review.getElectricOutletLevel().name(),
                review.getWifiLevel().name(),
                review.getNoiseLevel().name(),
                review.getContent(),
                dateTimeAssembler.toOffsetDateTime(review.getCreatedAt()),
                dateTimeAssembler.toOffsetDateTime(review.getUpdatedAt())
        );
    }

    public ReviewCreateVo toReviewCreateVo(ReviewCreateRequest reviewCreateRequest) {
        return ReviewCreateVo.of(
                reviewCreateRequest.getElectricOutletLevel(),
                reviewCreateRequest.getWifiLevel(),
                reviewCreateRequest.getNoiseLevel(),
                reviewCreateRequest.getContent()
        );
    }

    public ReviewUpdateVo toReviewUpdateVo(ReviewUpdateRequest reviewUpdateRequest) {
        return ReviewUpdateVo.of(
                reviewUpdateRequest.getElectricOutletLevel(),
                reviewUpdateRequest.getWifiLevel(),
                reviewUpdateRequest.getNoiseLevel(),
                reviewUpdateRequest.getContent()
        );
    }
}
