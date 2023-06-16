package kr.co.yapp._22nd.coffice.notification.firebase;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ImportAutoConfiguration(FirebaseMessagingAutoConfiguration.class)
public @interface EnableFirebaseMessaging {
}
