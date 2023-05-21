package kr.co.yapp.cafe.domain.scrapping;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScrappingResultResultServiceImpl implements ScrappingResultService {
    private final ScrappingRepository scrappingRepository;


    @Override
    @Transactional
    public ScrappingResult create(ScrappingResultCreateVo scrappingResultCreateVo) {
        // TODO: validation
        ScrappingResult scrappingResult = ScrappingResult.from(scrappingResultCreateVo);
        return scrappingRepository.save(scrappingResult);
    }

    @Override
    public Page<ScrappingResult> findAll(Pageable pageable) {
        return scrappingRepository.findAll(pageable);
    }
}
