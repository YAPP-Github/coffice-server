package kr.co.yapp.cafe.domain.scrapping;

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
