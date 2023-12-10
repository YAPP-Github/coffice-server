package kr.co.yapp._22nd.coffice.ui.place;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import kr.co.yapp._22nd.coffice.application.NaverPlaceSearchService;
import kr.co.yapp._22nd.coffice.application.PlaceApplicationService;
import kr.co.yapp._22nd.coffice.domain.DoubleCursorPageable;
import kr.co.yapp._22nd.coffice.infrastructure.springdoc.SpringdocConfig;
import kr.co.yapp._22nd.coffice.ui.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = SpringdocConfig.SECURITY_SCHEME_NAME)
@RequestMapping("/api/v1/places/search")
@RestController
@RequiredArgsConstructor
public class PlaceSearchController {
    private final PlaceApplicationService placeApplicationService;
    private final PlaceSearchAssembler placeSearchAssembler;
    private final NaverPlaceSearchService naverPlaceSearchService;

    @PostMapping
    public ApiResponse<List<PlaceSearchResponse>> search(
            @AuthenticationPrincipal Long memberId,
            @RequestBody @Valid PlaceSearchRequest placeSearchRequest
    ) {
        return ApiResponse.success(
                placeApplicationService.search(
                        memberId,
                        placeSearchAssembler.toPlaceSearchRequestVo(placeSearchRequest),
                        DoubleCursorPageable.of(
                                placeSearchRequest.getLastSeenDistance(),
                                placeSearchRequest.getPageSize()
                        )
                ).map(placeSearchAssembler::toPlaceSearchResponse)
        );
    }

    @GetMapping("/naver-places")
    public ApiResponse<?> naverPlaces(
            @AuthenticationPrincipal Long memberId,
            @RequestParam String name
    ) {
        return ApiResponse.success(
                naverPlaceSearchService.search(name)
        );
    }
}
