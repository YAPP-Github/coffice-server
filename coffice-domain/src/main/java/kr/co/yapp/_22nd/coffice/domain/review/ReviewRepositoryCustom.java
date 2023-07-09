package kr.co.yapp._22nd.coffice.domain.review;

import kr.co.yapp._22nd.coffice.domain.CursorPageable;
import org.springframework.data.domain.Slice;

public interface ReviewRepositoryCustom {
    Slice<Review> findByMemberIdAndPlaceIdOrderByReviewIdDesc(Long memberId, Long placeId, CursorPageable<Long> cursorPageable);
}
