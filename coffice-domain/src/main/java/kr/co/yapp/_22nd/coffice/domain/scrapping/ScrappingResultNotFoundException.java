package kr.co.yapp._22nd.coffice.domain.scrapping;

import kr.co.yapp._22nd.coffice.domain.NotFoundException;

public class ScrappingResultNotFoundException extends NotFoundException {
    public ScrappingResultNotFoundException(Long scrappingResultId) {
        super("scrappingResult not found. scrappingResultId: " + scrappingResultId);
    }
}
