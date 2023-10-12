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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
            Map<Long, List<String>> map = amazonS3.listObjects("coffice-images").getObjectSummaries().stream().filter(it -> {
                String key = it.getKey();
                Matcher matcher = PLACE_IMAGE_NAME_PATTERN.matcher(key);
                return matcher.matches();
            }).collect(Collectors.toMap(
                    it -> {
                        Matcher matcher = PLACE_IMAGE_NAME_PATTERN.matcher(it.getKey());
                        try {
                            if (matcher.matches()) {
                                return Long.parseLong(matcher.group(1));
                            } else {
                                throw new RuntimeException("Failed to parse placeId from key: " + it.getKey());
                            }
                        } catch (Exception e) {
                            log.error("Failed to parse placeId from key: {}", it.getKey(), e);
                            throw e;
                        }
                    },
                    it -> Collections.singletonList("https://coffice-images.s3.ap-northeast-2.amazonaws.com/" + it.getKey()),
                    (a, b) -> {
                        List<String> urls = new ArrayList<>();
                        urls.addAll(a);
                        urls.addAll(b);
                        return urls;
                    }
            ));
            map.forEach((placeId, urls) -> {
                placeCommandService.removeImages(placeId);
                urls.forEach(it -> {
                    placeCommandService.addImage(placeId, it);
                });
            });
            return RepeatStatus.FINISHED;
        };
    }

}
