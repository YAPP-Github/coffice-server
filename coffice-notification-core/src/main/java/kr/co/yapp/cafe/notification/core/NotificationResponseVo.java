package kr.co.yapp.cafe.notification.core;

import lombok.Value;

@Value
public class NotificationResponseVo {
    boolean isSuccess;
    String message;

    private NotificationResponseVo(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public static NotificationResponseVo success(String message) {
        return new NotificationResponseVo(true, message);
    }

    public static NotificationResponseVo failure(String message) {
        return new NotificationResponseVo(false, message);
    }
}
