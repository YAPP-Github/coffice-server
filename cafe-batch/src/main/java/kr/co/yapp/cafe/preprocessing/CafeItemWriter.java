package kr.co.yapp.cafe.preprocessing;

import kr.co.yapp.cafe.domain.scrapping.ScrappingResultCreateVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

@Slf4j
public class CafeItemWriter implements ItemWriter<ScrappingResultCreateVo> {
    @Override
    public void write(Chunk<? extends ScrappingResultCreateVo> chunk) throws Exception {
        log.info("{}", chunk);
    }
}
