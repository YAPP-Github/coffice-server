package kr.co.yapp._22nd.coffice.domain.review;

import kr.co.yapp._22nd.coffice.domain.CursorPageable;
import org.springframework.data.domain.Slice;

public interface ReviewService {
    Slice<Review> findByMemberIdAndPlaceId(Long memberId, Long placeId, CursorPageable<Long> cursorPageable);

    Review getByReviewId(Long reviewId);

    Review create(Long memberId, Long placeId, ReviewCreateVo reviewCreateVo);

    Review update(Long memberId, Long placeId, Long reviewId, ReviewUpdateVo reviewUpdateVo);

    void delete(Long memberId, Long placeId, Long reviewId);
}
