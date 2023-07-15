package kr.co.yapp._22nd.coffice.domain.review;

import kr.co.yapp._22nd.coffice.domain.CursorPageable;
import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.member.MemberQueryService;
import kr.co.yapp._22nd.coffice.domain.place.Place;
import kr.co.yapp._22nd.coffice.domain.place.PlaceQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberQueryService memberQueryService;
    private final PlaceQueryService placeQueryService;

    @Override
    public Slice<Review> findByPlaceId(
            Long memberId,
            Long placeId,
            CursorPageable<Long> cursorPageable
    ) {
        return reviewRepository.findByPlaceIdOrderByReviewIdDesc(memberId, placeId, cursorPageable);
    }

    @Override
    public Review getByReviewId(Long reviewId) {
        return reviewRepository.findByReviewIdAndDeletedFalse(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException(reviewId));
    }

    @Override
    @Transactional
    public Review create(
            Long memberId,
            Long placeId,
            ReviewCreateVo reviewCreateVo
    ) {
        Member member = memberQueryService.getMember(memberId);
        Place place = placeQueryService.getPlace(placeId);
        Review review = Review.of(member, place, reviewCreateVo);
        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    public Review update(
            Long memberId,
            Long placeId,
            Long reviewId,
            ReviewUpdateVo reviewUpdateVo
    ) {
        return reviewRepository.findByMember_memberIdAndPlace_placeIdAndReviewId(memberId, placeId, reviewId)
                .map(it -> it.update(reviewUpdateVo))
                .orElseThrow(() -> new ReviewNotFoundException(reviewId));
    }

    @Override
    @Transactional
    public void delete(
            Long memberId,
            Long placeId,
            Long reviewId
    ) {
        reviewRepository.findByMember_memberIdAndPlace_placeIdAndReviewId(memberId, placeId, reviewId)
                .ifPresent(Review::delete);
    }
}
