package kr.co.yapp.cafe.controller;

import kr.co.yapp.cafe.controller.dto.ScrappingResultCreateRequest;
import kr.co.yapp.cafe.controller.dto.ScrappingResultResponse;
import kr.co.yapp.cafe.domain.scrapping.ScrappingResult;
import kr.co.yapp.cafe.domain.scrapping.ScrappingResultCreateVo;
import kr.co.yapp.cafe.domain.scrapping.ScrappingResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RequestMapping("/api/v1/scrapping-results")
@RestController
@RequiredArgsConstructor
public class ScrappingResultController {
    private final ScrappingResultService scrappingResultService;

    @GetMapping
    public List<ScrappingResultResponse> read() {
//        return Collections.emptyList();
        return List.of("name1", "name2", "name3").stream().map(
                (it) -> {
                    return new ScrappingResultResponse(1L, it);
                }
        ).toList();
    }

    @PostMapping
    public ScrappingResultResponse create(
            @RequestBody ScrappingResultCreateRequest scrappingResultCreateRequest
    ) {
        ScrappingResult scrappingResult = scrappingResultService.create(
                ScrappingResultCreateVo.of(
                        scrappingResultCreateRequest.getName(),
                        null,
                        null,
                        Collections.emptyList(),
                        Collections.emptyList()
                )
        );
        return new ScrappingResultResponse(
                scrappingResult.getScrappingResultId(),
                scrappingResult.getName()
        );
    }
}
