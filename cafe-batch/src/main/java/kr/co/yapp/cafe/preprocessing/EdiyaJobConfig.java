package kr.co.yapp.cafe.preprocessing;

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
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@ConditionalOnProperty(
        name = "spring.batch.job.names",
        havingValue = EdiyaJobConfig.JOB_NAME
)
@Configuration
@RequiredArgsConstructor
public class EdiyaJobConfig {
    static final String JOB_NAME = "ediyaJob";
    private static final String STEP_NAME = "ediyaStep";
    private final JobRepository jobRepository;

    @Bean
    public Job ediyaJob() {
        return new JobBuilder(JOB_NAME, jobRepository)
                .start(ediyaStep())
                .build();
    }

    @Bean
    @JobScope
    public Step ediyaStep() {
        return new StepBuilder(STEP_NAME, jobRepository)
                .tasklet(ediyaTasklet(), new ResourcelessTransactionManager())
                .build();
    }

    @Bean
    @StepScope
    public Tasklet ediyaTasklet() {
        return new EdiyaTasklet();
    }

}
