package kr.co.yapp.cafe.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScrappingResultResponse {
    private Long scrappingResultId;
    private String name;
}
