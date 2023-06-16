package kr.co.yapp.cafe.notification.core;

import lombok.Value;

@Value(staticConstructor = "of")
public class NotificationRequestVo {
    String title;
    String content;
    String fcmToken;
}
