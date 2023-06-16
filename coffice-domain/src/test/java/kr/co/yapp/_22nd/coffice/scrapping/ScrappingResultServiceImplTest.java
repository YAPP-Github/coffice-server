package kr.co.yapp._22nd.coffice.scrapping;

import kr.co.yapp._22nd.coffice.domain.place.Address;
import kr.co.yapp._22nd.coffice.domain.place.Coordinates;
import kr.co.yapp._22nd.coffice.domain.scrapping.ScrappingResult;
import kr.co.yapp._22nd.coffice.domain.scrapping.ScrappingResultCreateVo;
import kr.co.yapp._22nd.coffice.domain.scrapping.ScrappingResultService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@SpringBootTest
class ScrappingResultServiceImplTest {
    @Autowired
    private ScrappingResultService scrappingResultService;

    @DisplayName("create: 성공")
    @Test
    void create() {
        scrappingResultService.create(
                ScrappingResultCreateVo.of(
                        "어반코코",
                        Address.builder()
                                .streetAddress("경기 성남시 중원구 광명로42번길 30 코코하우스 1F, B1")
                                .landAddress("성남동 3700")
                                .postalCode("13368")
                                .build(),
                        Coordinates.of(
                                37.4312998d,
                                127.134258d
                        ),
                        Collections.singletonList(
                                "031-752-6457"
                        ),
                        Arrays.asList(
                                "https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20180920_168%2F1537412782135oc6UF_JPEG%2FKpQaUHn-sjOogruAwuSAHkEl.jpg",
                                "https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20170819_52%2F1503142330877D7Flo_JPEG%2Fr3xuX9zC22jh8UZ-GFKPZbXl.jpg",
                                "https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20221220_48%2F167153123560131V9N_JPEG%2F1592133356230-3.jpg"
                        )
                )
        );

        List<ScrappingResult> results = scrappingResultService.findAll(Pageable.unpaged()).getContent();
        Assertions.assertThat(results).isNotEmpty();
    }
}
