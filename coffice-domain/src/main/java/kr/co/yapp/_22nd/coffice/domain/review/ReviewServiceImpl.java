package kr.co.yapp._22nd.coffice.domain.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public Review getByReviewId(Long reviewId) {
        return reviewRepository.findByReviewIdAndDeletedFalse(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException(reviewId));
    }
}
