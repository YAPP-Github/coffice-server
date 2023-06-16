package kr.co.yapp.cafe.notification;

import kr.co.yapp.cafe.notification.firebase.EnableFirebaseMessaging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
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
        havingValue = NotificationJobConfig.JOB_NAME
)
@EnableFirebaseMessaging
@Configuration
@RequiredArgsConstructor
public class NotificationJobConfig {
    static final String JOB_NAME = "notificationJob";

    private final JobRepository jobRepository;

    @Bean
    public Job notificationJob() throws Exception {
        return new JobBuilder(JOB_NAME, jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(notificationStep())
                .build();
    }

    @Bean
    @JobScope
    public Step notificationStep() throws Exception {
        return new StepBuilder("notificationStep", jobRepository)
                .tasklet(notificationTasklet(), new ResourcelessTransactionManager())
                .build();
    }

    @Bean
    @StepScope
    public Tasklet notificationTasklet() {
        return new NotificationTasklet();
    }

}
