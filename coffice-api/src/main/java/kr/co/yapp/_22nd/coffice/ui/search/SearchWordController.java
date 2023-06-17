package kr.co.yapp._22nd.coffice.ui.search;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kr.co.yapp._22nd.coffice.application.search.SearchWordApplicationService;
import kr.co.yapp._22nd.coffice.infrastructure.springdoc.SpringdocConfig;
import kr.co.yapp._22nd.coffice.ui.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@SecurityRequirement(name = SpringdocConfig.SECURITY_SCHEME_NAME)
@RequestMapping("/api/v1/search-words")
@RestController
@RequiredArgsConstructor
public class SearchWordController {
    private final SearchWordApplicationService searchWordApplicationService;
    private final SearchWordAssembler searchWordAssembler;

    @DeleteMapping
    public ApiResponse<?> deleteAll(
            @AuthenticationPrincipal Long memberId
    ) {
        searchWordApplicationService.deleteAll(memberId);
        return ApiResponse.success();
    }

    @DeleteMapping("/{searchWordId}")
    public ApiResponse<?> delete(
            @AuthenticationPrincipal Long memberId,
            @PathVariable Long searchWordId
    ) {
        searchWordApplicationService.delete(memberId, searchWordId);
        return ApiResponse.success();
    }

    @GetMapping
    public ApiResponse<List<SearchWordResponse>> getSearchWords(
            @AuthenticationPrincipal Long memberId
    ) {
        return ApiResponse.success(
                searchWordApplicationService.findByMemberId(memberId)
                        .stream()
                        .map(searchWordAssembler::toSearchWordResponse)
                        .collect(Collectors.toList())
        );
    }
}
