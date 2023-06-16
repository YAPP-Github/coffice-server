package kr.co.yapp._22nd.coffice.notification.firebase;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import kr.co.yapp._22nd.coffice.notification.core.NotificationRequestVo;
import kr.co.yapp._22nd.coffice.notification.core.NotificationResponseVo;
import kr.co.yapp._22nd.coffice.notification.core.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class FirebaseNotificationService implements NotificationService {
    private final FirebaseMessaging firebaseMessaging;

    @Override
    public NotificationResponseVo send(NotificationRequestVo notificationRequestVo) {
        try {
            String resultMessage = firebaseMessaging.send(
                    Message.builder()
                            .setNotification(
                                    Notification.builder()
                                            .setTitle(notificationRequestVo.getTitle())
                                            .setBody(notificationRequestVo.getContent())
                                            .build()
                            )
                            .setToken(notificationRequestVo.getFcmToken())
                            .build()
            );
            log.debug("resultMessage: {}", resultMessage);
            return NotificationResponseVo.success(resultMessage);
        } catch (Exception e) {
            log.error("Failed to send FCM notification", e);
            return NotificationResponseVo.failure(
                    e.getMessage() != null ? e.getMessage() : "Failed to send FCM notification"
            );
        }
    }
}

