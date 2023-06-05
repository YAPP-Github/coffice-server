package kr.co.yapp.cafe.domain.scrapping;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

public class ScrappingResultRepositoryImpl extends QuerydslRepositorySupport implements ScrappingResultRepositoryCustom {

    private final QScrappingResult qScrappingResult = QScrappingResult.scrappingResult;

    public ScrappingResultRepositoryImpl() {
        super(ScrappingResult.class);
    }

    @Override
    public Optional<ScrappingResult> findByScrappingResultId(Long scrappingResultId) {
        ScrappingResult scrappingResult = from(qScrappingResult)
                .where(qScrappingResult.scrappingResultId.eq(scrappingResultId))
                .fetchOne();
        return Optional.ofNullable(scrappingResult);
    }
}
