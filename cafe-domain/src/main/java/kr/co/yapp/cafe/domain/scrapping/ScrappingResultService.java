package kr.co.yapp.cafe.domain.scrapping;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScrappingResultService {
    ScrappingResult create(ScrappingResultCreateVo scrappingResultCreateVo);

    Page<ScrappingResult> findAll(Pageable pageable);
}
