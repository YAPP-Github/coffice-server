package kr.co.yapp.cafe.ui.review;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
public class ReviewController {
    /**
     * 특정 장소의 리뷰 목록 조회
     *
     * @param authorization Bearer ${accessToken}
     * @param placeId       장소 식별자
     * @param pageable      페이지 정보
     * @return 리뷰 목록
     */
    @GetMapping("/places/{placeId}/reviews")
    public Object getReviews(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long placeId,
            @PageableDefault Pageable pageable
    ) {
        // TODO: 특정 장소의 리뷰 목록 조회
        return null;
    }

    /**
     * 내가 쓴 리뷰 목록 조회
     *
     * @param authorization Bearer ${accessToken}
     * @param pageable      페이지 정보
     * @return 리뷰 목록
     */
    @GetMapping("/members/me/reviews")
    public Object getMyReviews(
            @RequestHeader("Authorization") String authorization,
            @PageableDefault Pageable pageable
    ) {
        // TODO: 내가 쓴 리뷰 목록 조회
        return null;
    }

    /**
     * 리뷰 작성
     *
     * @param authorization       Bearer ${accessToken}
     * @param placeId             장소 식별자
     * @param reviewCreateRequest 리뷰 작성 정보
     * @return 작성된 리뷰
     */
    @PostMapping("/places/{placeId}/reviews")
    public Object createReview(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long placeId,
            @RequestBody ReviewCreateRequest reviewCreateRequest
    ) {
        // TODO: 리뷰 작성
        return null;
    }

    /**
     * 리뷰 수정
     *
     * @param authorization       Bearer ${accessToken}
     * @param reviewId            리뷰 식별자
     * @param reviewUpdateRequest 리뷰 수정 정보
     * @return 수정된 리뷰
     */
    @PutMapping("/reviews/{reviewId}")
    public Object updateReview(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long reviewId,
            @RequestBody ReviewUpdateRequest reviewUpdateRequest
    ) {
        // TODO: 리뷰 수정
        return null;
    }

    /**
     * 리뷰 삭제
     *
     * @param authorization Bearer ${accessToken}
     * @param reviewId      리뷰 식별자
     */
    @DeleteMapping("/reviews/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long reviewId
    ) {
        // TODO: 리뷰 삭제
    }


    /**
     * 리뷰 신고
     *
     * @param authorization       Bearer ${accessToken}
     * @param reviewId            리뷰 식별자
     * @param reviewReportRequest 리뷰 신고 정보
     * @return 신고된 리뷰
     */
    @PostMapping("/reviews/{reviewId}/report")
    public Object reportReview(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long reviewId,
            @RequestBody ReviewReportRequest reviewReportRequest
    ) {
        // TODO: 리뷰 신고
        return null;
    }
}
