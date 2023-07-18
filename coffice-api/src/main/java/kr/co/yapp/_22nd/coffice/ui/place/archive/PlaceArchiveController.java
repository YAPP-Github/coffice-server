package kr.co.yapp._22nd.coffice.ui.place.archive;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kr.co.yapp._22nd.coffice.application.PlaceArchiveApplicationService;
import kr.co.yapp._22nd.coffice.domain.place.Place;
import kr.co.yapp._22nd.coffice.infrastructure.springdoc.SpringdocConfig;
import kr.co.yapp._22nd.coffice.ui.ApiResponse;
import kr.co.yapp._22nd.coffice.ui.place.PlaceAssembler;
import kr.co.yapp._22nd.coffice.ui.place.PlaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@SecurityRequirement(name = SpringdocConfig.SECURITY_SCHEME_NAME)
@RequestMapping("/api/v1/members/me/places")
@RestController
@RequiredArgsConstructor
public class PlaceArchiveController {
    private final PlaceArchiveApplicationService placeArchiveApplicationService;
    private final PlaceAssembler placeAssembler;

    @GetMapping
    public ApiResponse<List<PlaceResponse>> getArchivedPlaces(
            @AuthenticationPrincipal Long memberId
    ) {
        return ApiResponse.success(
                placeArchiveApplicationService.getArchivedPlaces(memberId)
                        .stream()
                        .map(it -> placeAssembler.toPlaceResponse(memberId, it))
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/{placeId}")
    public ApiResponse<PlaceResponse> add(
            @AuthenticationPrincipal Long memberId,
            @PathVariable Long placeId
    ) {
        Place place = placeArchiveApplicationService.addArchivedPlace(memberId, placeId);
        PlaceResponse placeResponse = placeAssembler.toPlaceResponse(memberId, place);
        return ApiResponse.success(placeResponse);
    }

    @DeleteMapping("/{placeId}")
    public ApiResponse<Object> remove(
            @AuthenticationPrincipal Long memberId,
            @PathVariable Long placeId
    ) {
        placeArchiveApplicationService.removeArchivedPlace(memberId, placeId);
        return ApiResponse.success();
    }

    @DeleteMapping
    public ApiResponse<Object> remove(
            @AuthenticationPrincipal Long memberId,
            @RequestBody ArchivedPostDeleteRequest archivedPostDeleteRequest
    ) {
        placeArchiveApplicationService.removeArchivedPlaces(memberId, archivedPostDeleteRequest.getPostIds());
        return ApiResponse.success();
    }
}
