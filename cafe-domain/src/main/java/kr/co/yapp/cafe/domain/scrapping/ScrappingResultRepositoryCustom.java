package kr.co.yapp.cafe.domain.scrapping;

import java.util.Optional;

public interface ScrappingResultRepositoryCustom {
    Optional<ScrappingResult> findByScrappingResultId(Long scrappingResultId);
}
