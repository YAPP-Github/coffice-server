package kr.co.yapp.cafe.ui.place.folder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kr.co.yapp.cafe.application.PlaceFolderApplicationService;
import kr.co.yapp.cafe.domain.place.folder.PlaceFolder;
import kr.co.yapp.cafe.infrastructure.springdoc.SpringdocConfig;
import kr.co.yapp.cafe.ui.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@SecurityRequirement(name = SpringdocConfig.SECURITY_SCHEME_NAME)
@RequestMapping("/api/v1/place-folders")
@RestController
@RequiredArgsConstructor
public class PlaceFolderController {
    private final PlaceFolderApplicationService placeFolderApplicationService;
    private final PlaceFolderAssembler placeFolderAssembler;

    /**
     * 장소 폴더 목록 조회
     *
     * @return 장소 폴더 목록
     */
    @GetMapping
    public ApiResponse<List<PlaceFolderResponse>> getPlaceFolders(
            @AuthenticationPrincipal Long memberId
    ) {
        return ApiResponse.success(
                placeFolderApplicationService.getPlaceFolders(memberId)
                        .stream()
                        .map(placeFolderAssembler::toPlaceFolderResponse)
                        .collect(Collectors.toList())
        );
    }

    /**
     * 장소 폴더 조회
     *
     * @param placeFolderId 장소 폴더 식별자
     * @return 장소 폴더
     */
    @GetMapping("/{placeFolderId}")
    public ApiResponse<PlaceFolderResponse> getPlaceFolder(
            @AuthenticationPrincipal Long memberId,
            @PathVariable Long placeFolderId
    ) {
        PlaceFolder placeFolder = placeFolderApplicationService.getPlaceFolder(memberId, placeFolderId);
        PlaceFolderResponse placeFolderResponse = placeFolderAssembler.toPlaceFolderResponse(placeFolder);
        return ApiResponse.success(placeFolderResponse);
    }

    /**
     * 장소 폴더 생성
     *
     * @param placeFolderCreateRequest 장소 폴더 생성 정보
     * @return 생성된 장소 폴더
     */
    @PostMapping
    public ApiResponse<PlaceFolderResponse> createPlaceFolder(
            @AuthenticationPrincipal Long memberId,
            @RequestBody PlaceFolderCreateRequest placeFolderCreateRequest
    ) {
        PlaceFolder placeFolder = placeFolderApplicationService.create(
                memberId,
                placeFolderAssembler.toPlaceFolderCreateVo(placeFolderCreateRequest)
        );
        PlaceFolderResponse placeFolderResponse = placeFolderAssembler.toPlaceFolderResponse(placeFolder);
        return ApiResponse.success(placeFolderResponse);
    }

    /**
     * 장소 폴더 수정
     *
     * @param placeFolderId            장소 폴더 식별자
     * @param placeFolderUpdateRequest 장소 폴더 수정 정보
     * @return 수정된 장소 폴더
     */
    @PutMapping("/{placeFolderId}")
    public Object updatePlaceFolder(
            @AuthenticationPrincipal Long memberId,
            @PathVariable Long placeFolderId,
            @RequestBody PlaceFolderUpdateRequest placeFolderUpdateRequest
    ) {
        PlaceFolder placeFolder = placeFolderApplicationService.update(
                memberId,
                placeFolderId,
                placeFolderAssembler.toPlaceFolderUpdateVo(placeFolderUpdateRequest)
        );
        PlaceFolderResponse placeFolderResponse = placeFolderAssembler.toPlaceFolderResponse(placeFolder);
        return ApiResponse.success(placeFolderResponse);
    }

    /**
     * 장소 폴더 삭제
     *
     * @param placeFolderId 장소 폴더 식별자
     */
    @DeleteMapping("/{placeFolderId}")
    public ApiResponse<?> deletePlaceFolder(
            @AuthenticationPrincipal Long memberId,
            @PathVariable Long placeFolderId
    ) {
        placeFolderApplicationService.delete(memberId, placeFolderId);
        return ApiResponse.success();
    }
}
