package kr.co.yapp._22nd.coffice.domain.review;

import com.querydsl.core.types.dsl.BooleanExpression;
import kr.co.yapp._22nd.coffice.domain.CursorPageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ReviewRepositoryImpl extends QuerydslRepositorySupport implements ReviewRepositoryCustom {
    private final QReview qReview = QReview.review;

    public ReviewRepositoryImpl() {
        super(Review.class);
    }

    @Override
    public Slice<Review> findByMemberIdAndPlaceIdOrderByReviewIdDesc(
            Long memberId,
            Long placeId,
            CursorPageable<Long> cursorPageable
    ) {
        BooleanExpression booleanExpression = qReview.member.memberId.eq(memberId)
                .and(qReview.place.placeId.eq(placeId))
                .and(qReview.deleted.isFalse());
        if (!cursorPageable.isInitial()) {
            booleanExpression = booleanExpression.and(qReview.reviewId.lt(cursorPageable.getLastSeenKey()));
        }
        List<Review> reviews = from(qReview)
                .where(booleanExpression)
                .orderBy(qReview.reviewId.desc())
                .limit(cursorPageable.getPageSize() + 1)
                .fetch();
        boolean hasNext = reviews.size() > cursorPageable.getPageSize();
        return new SliceImpl<>(
                hasNext ? reviews.subList(0, cursorPageable.getPageSize()) : reviews,
                cursorPageable.toPageable(),
                hasNext
        );
    }
}
