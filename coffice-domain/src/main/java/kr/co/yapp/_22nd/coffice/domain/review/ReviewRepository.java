package kr.co.yapp._22nd.coffice.domain.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByReviewIdAndDeletedFalse(Long reviewId);
}
