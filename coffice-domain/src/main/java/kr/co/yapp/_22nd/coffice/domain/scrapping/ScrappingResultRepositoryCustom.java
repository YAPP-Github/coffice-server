package kr.co.yapp._22nd.coffice.domain.scrapping;

import java.util.Optional;

public interface ScrappingResultRepositoryCustom {
    Optional<ScrappingResult> findByScrappingResultId(Long scrappingResultId);
}
