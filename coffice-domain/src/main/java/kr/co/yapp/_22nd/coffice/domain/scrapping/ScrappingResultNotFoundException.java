package kr.co.yapp._22nd.coffice.domain.scrapping;

public class ScrappingResultNotFoundException extends RuntimeException {
    private final Long scrappingResultId;

    public ScrappingResultNotFoundException(Long scrappingResultId) {
        this.scrappingResultId = scrappingResultId;
    }

    @Override
    public String getMessage() {
        return "'scrappingResult' not found. scrappingResultId: " + scrappingResultId;
    }
}
