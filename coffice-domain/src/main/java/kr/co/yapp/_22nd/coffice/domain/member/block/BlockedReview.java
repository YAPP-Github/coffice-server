package kr.co.yapp._22nd.coffice.domain.member.block;

import jakarta.persistence.*;
import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.review.Review;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class BlockedReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blockedReviewId;
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "reviewId")
    private Review review;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private Boolean deleted = false;

    public static BlockedReview of(
            Member member,
            Review review
    ) {
        BlockedReview blockedReview = new BlockedReview();
        blockedReview.member = member;
        blockedReview.review = review;
        return blockedReview;
    }

    public void delete() {
        deleted = true;
    }
}
