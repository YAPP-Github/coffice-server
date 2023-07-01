package kr.co.yapp._22nd.coffice.ui.place;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import kr.co.yapp._22nd.coffice.application.PlaceApplicationService;
import kr.co.yapp._22nd.coffice.domain.DoubleCursorPageable;
import kr.co.yapp._22nd.coffice.infrastructure.springdoc.SpringdocConfig;
import kr.co.yapp._22nd.coffice.ui.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SecurityRequirement(name = SpringdocConfig.SECURITY_SCHEME_NAME)
@RequestMapping("/api/v1/places/search")
@RestController
@RequiredArgsConstructor
public class PlaceSearchController {
    private final PlaceApplicationService placeApplicationService;
    private final PlaceSearchAssembler placeSearchAssembler;

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
}
