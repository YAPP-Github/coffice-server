package kr.co.yapp.cafe.domain.scrapping;

import jakarta.persistence.*;
import kr.co.yapp.cafe.domain.place.Address;
import kr.co.yapp.cafe.domain.place.Coordinates;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 영업시간
 */
@Entity
@Getter
@ToString
@EqualsAndHashCode(of = "scrappingResultId")
@EntityListeners(AuditingEntityListener.class)
public class ScrappingResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrappingResultId;
    /**
     * 가게 이름
     */
    private String name;
    /**
     * 가게 주소
     */
    @Embedded
    private Address address;
    /**
     * 가게 좌표
     */
    @Embedded
    private Coordinates coordinates;
    /**
     * 가게 전화번호
     */
    @ElementCollection(fetch = FetchType.EAGER)
    private final List<String> contactNumbers = new ArrayList<>();
    /**
     * 가게 이미지 URL
     */
    @ElementCollection(fetch = FetchType.EAGER)
    private final List<String> imageUrls = new ArrayList<>();
    /**
     * 생성 시각
     */
    @CreatedDate
    private LocalDateTime createdAt;
    /**
     * 수정 시각
     */
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static ScrappingResult from(
            ScrappingResultCreateVo scrappingResultCreateVo
    ) {
        ScrappingResult scrappingResult = new ScrappingResult();
        scrappingResult.name = scrappingResultCreateVo.getName();
        scrappingResult.address = scrappingResultCreateVo.getAddress();
        scrappingResult.coordinates = scrappingResultCreateVo.getCoordinates();
        scrappingResult.contactNumbers.addAll(scrappingResultCreateVo.getContactNumbers());
        scrappingResult.imageUrls.addAll(scrappingResultCreateVo.getImageUrls());
        return scrappingResult;
    }
}
