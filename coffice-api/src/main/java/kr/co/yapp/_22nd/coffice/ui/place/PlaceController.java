package kr.co.yapp._22nd.coffice.ui.place;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kr.co.yapp._22nd.coffice.domain.place.*;
import kr.co.yapp._22nd.coffice.infrastructure.springdoc.SpringdocConfig;
import kr.co.yapp._22nd.coffice.ui.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = SpringdocConfig.SECURITY_SCHEME_NAME)
@RequestMapping("/api/v1/places")
@RestController
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;
    private final PlaceAssembler placeAssembler;

    @GetMapping
    public ApiResponse<List<PlaceResponse>> getPlaces(
            @AuthenticationPrincipal Long memberId,
            @PageableDefault Pageable pageable
    ) {
        return ApiResponse.success(
                placeService.findAll(pageable)
                        .map(placeAssembler::toPlaceResponse)
        );
    }

    @GetMapping("/{placeId}")
    public ApiResponse<PlaceResponse> getPlace(
            @PathVariable Long placeId
    ) {
        return ApiResponse.success(
                placeService.findById(placeId)
                        .map(placeAssembler::toPlaceResponse)
                        .orElseThrow(() -> new PlaceNotFoundException(placeId))
        );
    }

    @Deprecated
    @PostMapping
    public ApiResponse<PlaceResponse> createPlace(
            @RequestBody PlaceCreateRequest placeCreateRequest
    ) {
        PlaceCreateVo placeCreateVo = placeAssembler.toPlaceCreateVo(placeCreateRequest);
        Place place = placeService.create(placeCreateVo);
        PlaceResponse placeResponse = placeAssembler.toPlaceResponse(place);
        return ApiResponse.success(placeResponse);
    }

    @Deprecated
    @PutMapping("/{placeId}")
    public ApiResponse<PlaceResponse> updatePlace(
            @PathVariable Long placeId,
            @RequestBody PlaceUpdateRequest placeUpdateRequest
    ) {
        PlaceUpdateVo placeUpdateVo = placeAssembler.toPlaceUpdateVo(placeUpdateRequest);
        Place place = placeService.update(placeId, placeUpdateVo);
        PlaceResponse placeResponse = placeAssembler.toPlaceResponse(place);
        return ApiResponse.success(placeResponse);
    }
}
