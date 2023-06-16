package kr.co.yapp._22nd.coffice.notification.core;

import lombok.Value;

@Value(staticConstructor = "of")
public class NotificationRequestVo {
    String title;
    String content;
    String fcmToken;
}
