package kr.co.yapp.cafe.ui.folder;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/place-folders")
@RestController
public class PlaceFolderController {
    /**
     * 장소 폴더 목록 조회
     *
     * @param authorization Bearer ${accessToken}
     * @param pageable      페이지 정보
     * @return 장소 폴더 목록
     */
    @GetMapping
    public Object getPlaceFolders(
            @RequestHeader("Authorization") String authorization,
            @PageableDefault Pageable pageable
    ) {
        // TODO: 장소 폴더 목록 조회
        return null;
    }

    /**
     * 장소 폴더 조회
     *
     * @param authorization Bearer ${accessToken}
     * @param placeFolderId 장소 폴더 식별자
     * @return 장소 폴더
     */
    @GetMapping("/{placeFolderId}")
    public Object getPlaceFolder(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long placeFolderId
    ) {
        // TODO: 장소 폴더 조회
        return null;
    }

    /**
     * 장소 폴더 생성
     *
     * @param authorization            Bearer ${accessToken}
     * @param placeFolderCreateRequest 장소 폴더 생성 정보
     * @return 생성된 장소 폴더
     */
    @PostMapping
    public Object createPlaceFolder(
            @RequestHeader("Authorization") String authorization,
            @RequestBody PlaceFolderCreateRequest placeFolderCreateRequest
    ) {
        // TODO: 장소 폴더 생성
        return null;
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
        // TODO: 장소 폴더 수정
        return null;
    }

    /**
     * 장소 폴더 삭제
     *
     * @param authorization Bearer ${accessToken}
     * @param placeFolderId 장소 폴더 식별자
     */
    @DeleteMapping("/{placeFolderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlaceFolder(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long placeFolderId
    ) {
        // TODO: 장소 폴더 삭제
    }
}
