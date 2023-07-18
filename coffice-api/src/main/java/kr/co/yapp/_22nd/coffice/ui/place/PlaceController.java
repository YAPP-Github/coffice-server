package kr.co.yapp._22nd.coffice.ui.place;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import kr.co.yapp._22nd.coffice.application.PlaceApplicationService;
import kr.co.yapp._22nd.coffice.application.PlaceFolderPlaceApplicationService;
import kr.co.yapp._22nd.coffice.domain.place.Place;
import kr.co.yapp._22nd.coffice.domain.place.PlaceNotFoundException;
import kr.co.yapp._22nd.coffice.infrastructure.springdoc.SpringdocConfig;
import kr.co.yapp._22nd.coffice.ui.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = SpringdocConfig.SECURITY_SCHEME_NAME)
@RequestMapping("/api/v1/places")
@RestController
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceAssembler placeAssembler;
    private final PlaceApplicationService placeApplicationService;
    private final PlaceFolderPlaceApplicationService placeFolderPlaceApplicationService;
    private final PlaceFolderPlaceAssembler placeFolderPlaceAssembler;

    @GetMapping
    public ApiResponse<List<PlaceResponse>> getPlaces(
            @AuthenticationPrincipal Long memberId,
            @PageableDefault Pageable pageable,
            @RequestParam(required = false) String name
    ) {
        Slice<Place> placeSlice = placeApplicationService.findAll(
                placeAssembler.toPlaceQueryRequestVo(name),
                pageable
        );
        Slice<PlaceResponse> placeResponses = placeAssembler.toPlaceResponses(memberId, placeSlice);
        return ApiResponse.success(placeResponses);
    }

    @GetMapping("/{placeId}")
    public ApiResponse<PlaceResponse> getPlace(
            @AuthenticationPrincipal Long memberId,
            @PathVariable Long placeId
    ) {
        return ApiResponse.success(
                placeApplicationService.findById(placeId)
                        .map(it -> placeAssembler.toPlaceResponse(memberId, it))
                        .orElseThrow(() -> new PlaceNotFoundException(placeId))
        );
    }

    @Deprecated
    @PutMapping("/{placeId}/place-folders")
    public ApiResponse<List<PlaceFolderPlaceResponse>> updatePlaceToPlaceFolderMapping(
            @AuthenticationPrincipal Long memberId,
            @PathVariable Long placeId,
            @RequestBody PlaceToPlaceFolderMapRequest placeToPlaceFolderMapRequest
    ) {
        return ApiResponse.success(
                placeFolderPlaceApplicationService.updatePlaceToPlaceFolderMap(
                                memberId,
                                placeId,
                                placeToPlaceFolderMapRequest.getPlaceFolderIds()
                        )
                        .stream()
                        .map(it -> placeFolderPlaceAssembler.toPlaceFolderPlaceResponse(memberId, it))
                        .toList()
        );
    }

    /**
     * 폴더에 장소 저장
     *
     * @param memberId                회원 식별자
     * @param placeId                 장소 식별자
     * @param placeAddToFolderRequest 장소 저장 정보
     * @return 폴더, 장소 정보
     */
    @Deprecated
    @PostMapping("/{placeId}/save-to-folder")
    public ApiResponse<PlaceFolderPlaceResponse> savePlace(
            @AuthenticationPrincipal Long memberId,
            @PathVariable Long placeId,
            @RequestBody @Valid PlaceAddToFolderRequest placeAddToFolderRequest
    ) {
        return ApiResponse.success(
                placeFolderPlaceAssembler.toPlaceFolderPlaceResponse(
                        memberId,
                        placeFolderPlaceApplicationService.saveToPlaceFolder(
                                memberId,
                                placeAddToFolderRequest.getPlaceFolderId(),
                                placeId
                        )
                )
        );
    }

    /**
     * 폴더에서 장소 삭제
     *
     * @param memberId                     회원 식별자
     * @param placeId                      장소 식별자
     * @param placeDeleteFromFolderRequest 정소 삭제 정보
     * @return 없음
     */
    @Deprecated
    @PostMapping("/{placeId}/delete-from-folder")
    public ApiResponse<?> deletePlace(
            @AuthenticationPrincipal Long memberId,
            @PathVariable Long placeId,
            @RequestBody @Valid PlaceDeleteFromFolderRequest placeDeleteFromFolderRequest
    ) {
        placeFolderPlaceApplicationService.deleteFromPlaceFolder(
                memberId,
                placeDeleteFromFolderRequest.getPlaceFolderId(),
                placeId
        );
        return ApiResponse.success();
    }
}
