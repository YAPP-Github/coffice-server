package kr.co.yapp._22nd.coffice.migration.image;

import com.amazonaws.services.s3.AmazonS3;
import kr.co.yapp._22nd.coffice.domain.place.PlaceCommandService;
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
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PlaceImageMigrationJobConfig {
    static final String JOB_NAME = "placeImageMigrationJob";
    private static final Pattern PLACE_IMAGE_NAME_PATTERN = Pattern.compile("no(\\d+)_(\\d+)\\..*");
    private final JobRepository jobRepository;
    private final AmazonS3 amazonS3;
    private final PlaceCommandService placeCommandService;

    @Bean
    public Job placeImageMigrationJob() throws Exception {
        return new JobBuilder(JOB_NAME, jobRepository)
                .start(placeImageMigrationStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @JobScope
    @Bean
    public Step placeImageMigrationStep() {
        return new StepBuilder("placeImageMigrationStep", jobRepository)
                .tasklet(placeImageMigrationTasklet(), new ResourcelessTransactionManager())
                .build();
    }

    @StepScope
    @Bean
    public Tasklet placeImageMigrationTasklet() {
        return (contribution, chunkContext) -> {
            amazonS3.listObjects("coffice-images").getObjectSummaries().forEach(it -> {
                String key = it.getKey();
                String url = "https://coffice-images.s3.ap-northeast-2.amazonaws.com/" + it.getKey();
                log.info("url: {}", url);
                Matcher matcher = PLACE_IMAGE_NAME_PATTERN.matcher(key);
                if (matcher.matches()) {
                    String placeIdString = matcher.group(1);
                    Long placeId = Long.parseLong(placeIdString);
                    String imageNumberString = matcher.group(2);
                    Integer imageNumber = Integer.parseInt(imageNumberString);
                    log.info("placeId = {} , imageNumber = {}", placeId, imageNumber);
                    placeCommandService.addImage(placeId, url);
                }
            });
            return RepeatStatus.FINISHED;
        };
    }

}
