package kr.co.yapp.cafe.ui.place.folder;

import kr.co.yapp.cafe.application.PlaceFolderApplicationService;
import kr.co.yapp.cafe.domain.place.folder.PlaceFolder;
import kr.co.yapp.cafe.ui.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/place-folders")
@RestController
@RequiredArgsConstructor
public class PlaceFolderController {
    private final PlaceFolderApplicationService placeFolderApplicationService;
    private final PlaceFolderAssembler placeFolderAssembler;

    /**
     * 장소 폴더 목록 조회
     *
     * @param authorization Bearer ${accessToken}
     * @param pageable      페이지 정보
     * @return 장소 폴더 목록
     */
    @GetMapping
    public ApiResponse<List<PlaceFolderResponse>> getPlaceFolders(
            @RequestHeader("Authorization") String authorization,
            @PageableDefault Pageable pageable
    ) {
        Long memberId = resolveMemberId();
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
     * @param authorization Bearer ${accessToken}
     * @param placeFolderId 장소 폴더 식별자
     * @return 장소 폴더
     */
    @GetMapping("/{placeFolderId}")
    public ApiResponse<PlaceFolderResponse> getPlaceFolder(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long placeFolderId
    ) {
        Long memberId = resolveMemberId();
        PlaceFolder placeFolder = placeFolderApplicationService.getPlaceFolder(memberId, placeFolderId);
        PlaceFolderResponse placeFolderResponse = placeFolderAssembler.toPlaceFolderResponse(placeFolder);
        return ApiResponse.success(placeFolderResponse);
    }

    /**
     * 장소 폴더 생성
     *
     * @param authorization            Bearer ${accessToken}
     * @param placeFolderCreateRequest 장소 폴더 생성 정보
     * @return 생성된 장소 폴더
     */
    @PostMapping
    public ApiResponse<PlaceFolderResponse> createPlaceFolder(
            @RequestHeader("Authorization") String authorization,
            @RequestBody PlaceFolderCreateRequest placeFolderCreateRequest
    ) {
        Long memberId = resolveMemberId();
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
     * @param authorization            Bearer ${accessToken}
     * @param placeFolderId            장소 폴더 식별자
     * @param placeFolderUpdateRequest 장소 폴더 수정 정보
     * @return 수정된 장소 폴더
     */
    @PutMapping("/{placeFolderId}")
    public Object updatePlaceFolder(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long placeFolderId,
            @RequestBody PlaceFolderUpdateRequest placeFolderUpdateRequest
    ) {
        Long memberId = resolveMemberId();
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
     * @param authorization Bearer ${accessToken}
     * @param placeFolderId 장소 폴더 식별자
     */
    @DeleteMapping("/{placeFolderId}")
    public ApiResponse<?> deletePlaceFolder(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long placeFolderId
    ) {
        Long memberId = resolveMemberId();
        placeFolderApplicationService.delete(memberId, placeFolderId);
        return ApiResponse.success();
    }

    private Long resolveMemberId() {
        return 1L;
    }
}
