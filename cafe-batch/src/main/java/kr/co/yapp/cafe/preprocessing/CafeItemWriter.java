package kr.co.yapp.cafe.preprocessing;

import kr.co.yapp.cafe.domain.scrapping.ScrappingResult;
import kr.co.yapp.cafe.domain.scrapping.ScrappingResultCreateVo;
import kr.co.yapp.cafe.domain.scrapping.ScrappingResultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class CafeItemWriter implements ItemWriter<ScrappingResultCreateVo> {
    @Autowired
    private ScrappingResultRepository scrappingResultRepository;

    @Override
    public void write(Chunk<? extends ScrappingResultCreateVo> chunk) throws Exception {
        log.info("{}", chunk);
        List<ScrappingResult> scrappingResults = chunk.getItems()
                .stream()
                .map(ScrappingResult::from)
                .toList();
        scrappingResultRepository.saveAll(scrappingResults);
    }
}
