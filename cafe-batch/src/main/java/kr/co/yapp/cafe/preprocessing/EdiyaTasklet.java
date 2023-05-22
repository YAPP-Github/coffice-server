package kr.co.yapp.cafe.preprocessing;

import kr.co.yapp.cafe.domain.place.Address;
import kr.co.yapp.cafe.domain.place.Coordinates;
import kr.co.yapp.cafe.domain.scrapping.ScrappingResultCreateVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class EdiyaTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // read file
        File file = ResourceUtils.getFile("classpath:ediya.json");
        BufferedReader reader = Files.newBufferedReader(Path.of(file.getPath()));
        String content = reader.lines().collect(Collectors.joining());

        // parse
        JsonParser jsonParser = new BasicJsonParser();
        Map<String, Object> parsedMap = jsonParser.parseMap(content);
        List<Map<String, Object>> data = (List<Map<String, Object>>) parsedMap.get("data");
        List<ScrappingResultCreateVo> results = data.stream().map(it -> {
            Object name = it.get("storeNm");
            Object contactNumber = it.get("tel");
            Object address = it.get("address");
            Object latitude = it.get("latitude");
            Object longitude = it.get("longitude");
            Object imageUrl = it.get("imgPath");

            return ScrappingResultCreateVo.of(
                    name instanceof String && !"null".equalsIgnoreCase((String) name)
                            ? "이디야커피 " + ((String) name).trim()
                            : null,
                    address instanceof String && !"null".equalsIgnoreCase((String) address)
                            ? Address.builder().streetAddress((String) address).build()
                            : null,
                    latitude instanceof Double && longitude instanceof Double
                            ? Coordinates.of((Double) latitude, (Double) longitude)
                            : null,
                    contactNumber instanceof String && !"null".equalsIgnoreCase((String) contactNumber)
                            ? Collections.singletonList((String) contactNumber)
                            : Collections.emptyList(),
                    imageUrl instanceof String && !"null".equalsIgnoreCase((String) imageUrl)
                            ? Collections.singletonList((String) imageUrl)
                            : Collections.emptyList()
            );
        }).toList();

        // process
        for (ScrappingResultCreateVo result : results) {
            log.info("{}", result);
        }

        return RepeatStatus.FINISHED;
    }
}
