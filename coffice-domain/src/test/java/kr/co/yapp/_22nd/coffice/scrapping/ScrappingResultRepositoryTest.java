package kr.co.yapp._22nd.coffice.scrapping;

import kr.co.yapp._22nd.coffice.domain.scrapping.ScrappingResult;
import kr.co.yapp._22nd.coffice.domain.scrapping.ScrappingResultRepository;
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
    void test() {
        Optional<ScrappingResult> actual = scrappingResultRepository.findByScrappingResultId(0L);
        assertThat(actual).isEmpty();
    }
}
