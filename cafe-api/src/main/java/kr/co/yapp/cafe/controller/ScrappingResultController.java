package kr.co.yapp.cafe.controller;

import kr.co.yapp.cafe.controller.dto.ScrappingResultCreateRequest;
import kr.co.yapp.cafe.controller.dto.ScrappingResultResponse;
import kr.co.yapp.cafe.domain.scrapping.ScrappingResult;
import kr.co.yapp.cafe.domain.scrapping.ScrappingResultCreateVo;
import kr.co.yapp.cafe.domain.scrapping.ScrappingResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class ScrappingResultController {
    private final ScrappingResultService scrappingResultService;

    @PostMapping("/scrapping-results")
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
