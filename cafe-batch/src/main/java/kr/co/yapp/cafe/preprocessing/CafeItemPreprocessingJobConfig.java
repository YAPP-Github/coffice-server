package kr.co.yapp.cafe.preprocessing;

import kr.co.yapp.cafe.domain.scrapping.ScrappingResultCreateVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

@Slf4j
@ConditionalOnProperty(
        name = "spring.batch.job.names",
        havingValue = CafeItemPreprocessingJobConfig.JOB_NAME
)
@Configuration
@RequiredArgsConstructor
public class CafeItemPreprocessingJobConfig {
    static final String JOB_NAME = "cafeItemPreprocessingJob";
    private final JobRepository jobRepository;
    @Value("${cafe.filePath.arrayJsonInput}")
    private String arrayJsonInputFilePath;

    @Bean
    public Job preprocessingJob() {
        return new JobBuilder(JOB_NAME, jobRepository)
                .start(arrayExtractionStep())
                .next(preprocessingStep())
                .build();
    }

    /**
     * 입력받은 json 파일 중에서 배열 부분만 남긴다.
     */
    @Bean
    @JobScope
    public Step arrayExtractionStep() {
        return new StepBuilder("arrayExtractionStep", jobRepository)
                .tasklet(arrayExtranctionTasklet(), new ResourcelessTransactionManager())
                .build();
    }

    @Bean
    @StepScope
    public Tasklet arrayExtranctionTasklet() {
        return new JsonArrayExtractionTasklet();
    }

    /**
     * 배열 부분을 읽어서 ScrappingResultCreateVo로 변환 및 저장한다.
     */
    @Bean
    @JobScope
    public Step preprocessingStep() {
        return new StepBuilder("preprocessingStep", jobRepository)
                .<Object, ScrappingResultCreateVo>chunk(1, new ResourcelessTransactionManager())
                .reader(new JsonItemReaderBuilder<>()
                        .jsonObjectReader(new JacksonJsonObjectReader<>(Object.class))
                        .resource(new PathResource(arrayJsonInputFilePath))
                        .saveState(false)
                        .build())
                .processor(cafeItemProcessor())
                .writer(cafeItemWriter())
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<Object, ScrappingResultCreateVo> cafeItemProcessor() {
        return new CafeItemProcessor();
    }

    @Bean
    @StepScope
    public ItemWriter<ScrappingResultCreateVo> cafeItemWriter() {
        return new CafeItemWriter();
    }
}
