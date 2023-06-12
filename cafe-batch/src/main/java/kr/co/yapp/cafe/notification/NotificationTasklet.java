package kr.co.yapp.cafe.notification;

import kr.co.yapp.cafe.notification.core.NotificationRequestVo;
import kr.co.yapp.cafe.notification.core.NotificationResponseVo;
import kr.co.yapp.cafe.notification.core.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class NotificationTasklet implements Tasklet {
    @Autowired
    private NotificationService notificationService;
    @Value("#{jobParameters['title']}")
    private String title;
    @Value("#{jobParameters['content']}")
    private String content;
    @Value("#{jobParameters['fcmToken']}")
    private String fcmToken;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        NotificationRequestVo notificationRequestVo = NotificationRequestVo.of(title, content, fcmToken);
        log.info("request: {}", notificationRequestVo);
        NotificationResponseVo notificationResponseVo = notificationService.send(notificationRequestVo);
        log.info("response: {}", notificationResponseVo);
        return RepeatStatus.FINISHED;
    }
}
