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
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    public Job preprocessingJob() throws Exception {
        return new JobBuilder(JOB_NAME, jobRepository)
                .start(preprocessingStep())
                .build();
    }

    /**
     * 배열 부분을 읽어서 ScrappingResultCreateVo로 변환 및 저장한다.
     */
    @Bean
    @JobScope
    public Step preprocessingStep() throws Exception {
        return new StepBuilder("preprocessingStep", jobRepository)
                .<Object, ScrappingResultCreateVo>chunk(100, new ResourcelessTransactionManager())
                .reader(cafeItemReader(null, null))
                .processor(cafeItemProcessor())
                .writer(cafeItemWriter())
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<Object> cafeItemReader(
            @Value("#{jobParameters['filePath']}") String filePath,
            @Value("#{jobParameters['dataJsonPath']}") String dataJsonPath
    ) throws Exception {
        return new CafeItemReader(filePath, dataJsonPath);
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
