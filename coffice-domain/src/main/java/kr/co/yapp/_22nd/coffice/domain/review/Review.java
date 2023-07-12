package kr.co.yapp._22nd.coffice.domain.review;

import jakarta.persistence.*;
import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.place.ElectricOutletLevel;
import kr.co.yapp._22nd.coffice.domain.place.NoiseLevel;
import kr.co.yapp._22nd.coffice.domain.place.Place;
import kr.co.yapp._22nd.coffice.domain.place.WifiLevel;
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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "placeId")
    private Place place;

    /**
     * 콘센트 { 넉넉해요 / 적당해요 / 거의 없어요 }
     */
    @Enumerated(EnumType.STRING)
    private ElectricOutletLevel electricOutletLevel;

    /**
     * 와이파이 { 아쉬워요 / 빨라요 }
     */
    @Enumerated(EnumType.STRING)
    private WifiLevel wifiLevel;

    /**
     * 소음 { 조용해요 / 보통이에요 / 시끄러워요 }
     */
    @Enumerated(EnumType.STRING)
    private NoiseLevel noiseLevel;

    private String content;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    private Boolean deleted = false;

    public static Review of(
            Member member,
            Place place,
            ReviewCreateVo reviewCreateVo
    ) {
        Review review = new Review();
        review.member = member;
        review.place = place;
        review.electricOutletLevel = reviewCreateVo.getElectricOutletLevel();
        review.wifiLevel = reviewCreateVo.getWifiLevel();
        review.noiseLevel = reviewCreateVo.getNoiseLevel();
        review.content = reviewCreateVo.getContent();
        return review;
    }

    public Review update(
            ReviewUpdateVo reviewUpdateVo
    ) {
        electricOutletLevel = reviewUpdateVo.getElectricOutletLevel();
        wifiLevel = reviewUpdateVo.getWifiLevel();
        noiseLevel = reviewUpdateVo.getNoiseLevel();
        content = reviewUpdateVo.getContent();
        return this;
    }

    public void delete() {
        deleted = true;
    }
}
