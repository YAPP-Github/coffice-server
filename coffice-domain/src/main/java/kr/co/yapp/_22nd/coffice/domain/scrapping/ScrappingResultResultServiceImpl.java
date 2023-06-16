package kr.co.yapp._22nd.coffice.domain.scrapping;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScrappingResultResultServiceImpl implements ScrappingResultService {
    private final ScrappingResultRepository scrappingResultRepository;


    @Override
    @Transactional
    public ScrappingResult create(ScrappingResultCreateVo scrappingResultCreateVo) {
        // TODO: validation
        ScrappingResult scrappingResult = ScrappingResult.from(scrappingResultCreateVo);
        return scrappingResultRepository.save(scrappingResult);
    }

    @Override
    public Page<ScrappingResult> findAll(Pageable pageable) {
        return scrappingResultRepository.findAll(pageable);
    }

    @Override
    public ScrappingResult findById(Long scrappingResultId) {
        return scrappingResultRepository.findById(scrappingResultId)
                .orElseThrow(() -> new ScrappingResultNotFoundException(scrappingResultId));
    }
}
