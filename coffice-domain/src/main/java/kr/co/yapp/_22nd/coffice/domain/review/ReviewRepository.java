package kr.co.yapp._22nd.coffice.domain.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    Optional<Review> findByReviewIdAndDeletedFalse(Long reviewId);

    Optional<Review> findByMember_memberIdAndPlace_placeIdAndReviewId(Long memberId, Long placeId, Long reviewId);
}
