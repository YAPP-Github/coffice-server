package kr.co.yapp.cafe.controller;

import kr.co.yapp.cafe.controller.dto.ScrappingResultCreateRequest;
import kr.co.yapp.cafe.controller.dto.ScrappingResultResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1")
@RestController
public class ScrappingResultController {
    @PostMapping("/scrapping-results")
    public ScrappingResultResponse create(
            @RequestBody ScrappingResultCreateRequest scrappingResultCreateRequest
    ) {
        return new ScrappingResultResponse(
                1L,
                scrappingResultCreateRequest.getName()
        );
    }
}
