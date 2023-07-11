package kr.co.yapp._22nd.coffice.application;

import kr.co.yapp._22nd.coffice.domain.CursorPageable;
import kr.co.yapp._22nd.coffice.domain.review.Review;
import kr.co.yapp._22nd.coffice.domain.review.ReviewCreateVo;
import kr.co.yapp._22nd.coffice.domain.review.ReviewService;
import kr.co.yapp._22nd.coffice.domain.review.ReviewUpdateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewApplicationService {
    private final ReviewService reviewService;

    public Slice<Review> getReviewsByPlaceId(
            Long placeId,
            CursorPageable<Long> cursorPageable
    ) {
        return reviewService.findByPlaceId(placeId, cursorPageable);
    }

    public Review create(
            Long memberId,
            Long placeId,
            ReviewCreateVo reviewCreateVo
    ) {
        return reviewService.create(memberId, placeId, reviewCreateVo);
    }

    public Review update(
            Long memberId,
            Long placeId,
            Long reviewId,
            ReviewUpdateVo reviewUpdateVo
    ) {
        return reviewService.update(memberId, placeId, reviewId, reviewUpdateVo);
    }

    public void delete(
            Long memberId,
            Long placeId,
            Long reviewId
    ) {
        reviewService.delete(memberId, placeId, reviewId);
    }
}
