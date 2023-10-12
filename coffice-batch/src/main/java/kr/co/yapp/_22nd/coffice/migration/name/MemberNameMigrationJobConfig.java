package kr.co.yapp._22nd.coffice.migration.name;

import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.member.MemberCommandService;
import kr.co.yapp._22nd.coffice.domain.member.MemberQueryService;
import kr.co.yapp._22nd.coffice.domain.member.MemberRepository;
import kr.co.yapp._22nd.coffice.domain.member.name.MemberName;
import kr.co.yapp._22nd.coffice.domain.member.name.NameGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.regex.Pattern;

@Configuration
@RequiredArgsConstructor
public class MemberNameMigrationJobConfig {
    static final String JOB_NAME = "memberNameMigrationJob";
    public static final Pattern OLD_NAME_PATTERN = Pattern.compile("^\\D+\\d{3,6}$");
    private final JobRepository jobRepository;
    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;
    private final NameGenerationService nameGenerationService;
    private final MemberRepository memberRepository;

    @Bean
    public Job memberNameMigrationJob() throws Exception {
        return new JobBuilder(JOB_NAME, jobRepository)
                .start(memberNameMigrationStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step memberNameMigrationStep() {
        return new StepBuilder("memberNameMigrationStep", jobRepository)
                .tasklet(memberNameMigrationTasklet(), new ResourcelessTransactionManager())
                .build();
    }

    @Bean
    public Tasklet memberNameMigrationTasklet() {
        return (contribution, chunkContext) -> {
            List<Long> memberIds = memberQueryService.findAll(Pageable.unpaged())
                    .stream()
                    .filter(it -> isMigrationTarget(it.getName()))
                    .map(Member::getMemberId)
                    .toList();
            memberIds.forEach(it -> {
                String name;
                do {
                    name = nameGenerationService.generateRandomName();
                } while (!memberRepository.findByName(name).isEmpty());
                memberCommandService.updateName(it, MemberName.from(name));
            });
            return RepeatStatus.FINISHED;
        };
    }

    private boolean isMigrationTarget(String name) {
        if (name == null) {
            return true;
        }
        if ("test".equals(name)) {
            return true;
        }
        return OLD_NAME_PATTERN.matcher(name).matches();
    }
}
