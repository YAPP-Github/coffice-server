package kr.co.yapp.cafe.domain.scrapping;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ScrappingResultRepositoryTest {
    @Autowired
    private ScrappingResultRepository scrappingResultRepository;

    @Test
    void name() {
        Optional<ScrappingResult> actual = scrappingResultRepository.findByScrappingResultId(0L);
        assertThat(actual).isEmpty();
    }
}
