package kr.co.yapp.cafe.preprocessing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonArrayExtractionTasklet implements Tasklet {
    @Value("#{jobParameters['filename']}")
    private String filename;
    @Value("#{jobParameters['dataJsonPath']}")
    private String dataJsonPath;
    @Value("${cafe.filePath.arrayJsonOutput}")
    private String arrayJsonOutputFilePath;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        File file = ResourceUtils.getFile(filename);
        DocumentContext jsonContext = JsonPath.parse(file);
        Object read = jsonContext.read(dataJsonPath);
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(arrayJsonOutputFilePath))) {
            writer.write(new ObjectMapper().writeValueAsString(read));
        }
        return RepeatStatus.FINISHED;
    }
}
