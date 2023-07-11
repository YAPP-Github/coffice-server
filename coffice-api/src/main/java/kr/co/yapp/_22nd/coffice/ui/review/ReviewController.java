package kr.co.yapp._22nd.coffice.ui.review;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import kr.co.yapp._22nd.coffice.application.ReportApplicationService;
import kr.co.yapp._22nd.coffice.application.ReviewApplicationService;
import kr.co.yapp._22nd.coffice.domain.LongCursorPageable;
import kr.co.yapp._22nd.coffice.domain.review.Review;
import kr.co.yapp._22nd.coffice.infrastructure.springdoc.SpringdocConfig;
import kr.co.yapp._22nd.coffice.ui.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = SpringdocConfig.SECURITY_SCHEME_NAME)
@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReportApplicationService reportApplicationService;
    private final ReviewApplicationService reviewApplicationService;
    private final ReviewAssembler reviewAssembler;

    /**
     * 특정 장소의 리뷰 목록 조회
     *
     * @return 리뷰 목록
     */
    @GetMapping("/places/{placeId}/reviews")
    public ApiResponse<List<ReviewResponse>> getReviews(
            @AuthenticationPrincipal Long memberId,
            @PathVariable Long placeId,
            @RequestParam @Min(1) Integer pageSize,
            @RequestParam(required = false) Long lastSeenReviewId
    ) {
        return ApiResponse.success(
                reviewApplicationService.getReviewsByPlaceId(placeId, LongCursorPageable.of(lastSeenReviewId, pageSize))
                        .map(reviewAssembler::toReviewResponse)
        );
    }

    /**
     * 내가 쓴 리뷰 목록 조회
     *
     * @param pageable 페이지 정보
     * @return 리뷰 목록
     */
    @Deprecated
    @GetMapping("/members/me/reviews")
    public ApiResponse<List<ReviewResponse>> getMyReviews(
            @AuthenticationPrincipal Long memberId,
            @PageableDefault Pageable pageable
    ) {
        // TODO: 내가 쓴 리뷰 목록 조회
        return ApiResponse.success();
    }

    /**
     * 리뷰 작성
     *
     * @param placeId             장소 식별자
     * @param reviewCreateRequest 리뷰 작성 정보
     * @return 작성된 리뷰
     */
    @PostMapping("/places/{placeId}/reviews")
    public ApiResponse<ReviewResponse> createReview(
            @AuthenticationPrincipal Long memberId,
            @PathVariable Long placeId,
            @RequestBody @Valid ReviewCreateRequest reviewCreateRequest
    ) {
        Review review = reviewApplicationService.create(
                memberId,
                placeId,
                reviewAssembler.toReviewCreateVo(reviewCreateRequest)
        );
        ReviewResponse reviewResponse = reviewAssembler.toReviewResponse(review);
        return ApiResponse.success(reviewResponse);
    }

    /**
     * 리뷰 수정
     *
     * @param placeId             장소 식별자
     * @param reviewId            리뷰 식별자
     * @param reviewUpdateRequest 리뷰 수정 정보
     * @return 수정된 리뷰
     */
    @PutMapping("/places/{placeId}/reviews/{reviewId}")
    public ApiResponse<ReviewResponse> updateReview(
            @AuthenticationPrincipal Long memberId,
            @PathVariable Long placeId,
            @PathVariable Long reviewId,
            @RequestBody @Valid ReviewUpdateRequest reviewUpdateRequest
    ) {
        Review review = reviewApplicationService.update(
                memberId,
                placeId,
                reviewId,
                reviewAssembler.toReviewUpdateVo(reviewUpdateRequest)
        );
        ReviewResponse reviewResponse = reviewAssembler.toReviewResponse(review);
        return ApiResponse.success(reviewResponse);
    }

    /**
     * 리뷰 삭제
     *
     * @param reviewId 리뷰 식별자
     */
    @DeleteMapping("/places/{placeId}/reviews/{reviewId}")
    public ApiResponse<Object> deleteReview(
            @AuthenticationPrincipal Long memberId,
            @PathVariable Long placeId,
            @PathVariable Long reviewId
    ) {
        reviewApplicationService.delete(memberId, placeId, reviewId);
        return ApiResponse.success();
    }

    /**
     * 리뷰 신고
     */
    @PostMapping("/places/{placeId}/reviews/{reviewId}/report")
    public ApiResponse<Object> reportReview(
            @AuthenticationPrincipal Long memberId,
            @PathVariable Long placeId,
            @PathVariable Long reviewId
    ) {
        reportApplicationService.reportReview(memberId, reviewId);
        return ApiResponse.success();
    }
}
