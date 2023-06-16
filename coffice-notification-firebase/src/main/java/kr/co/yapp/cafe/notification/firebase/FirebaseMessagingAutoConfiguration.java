package kr.co.yapp.cafe.notification.firebase;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import kr.co.yapp.cafe.notification.core.NotificationService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;

@AutoConfiguration
public class FirebaseMessagingAutoConfiguration {
    @Bean
    public FirebaseProperties firebaseProperties() {
        return new FirebaseProperties();
    }

    @ConditionalOnMissingBean(FirebaseApp.class)
    @Bean
    public FirebaseApp firebaseApp(FirebaseProperties firebaseProperties) throws IOException {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(
                        ServiceAccountCredentials.newBuilder()
                                .setProjectId(firebaseProperties.getProjectId())
                                .setPrivateKeyId(firebaseProperties.getPrivateKeyId())
                                .setPrivateKeyString(decodeBase64(firebaseProperties.getPrivateKey()))
                                .setClientEmail(firebaseProperties.getClientEmail())
                                .setClientId(firebaseProperties.getClientId())
                                .setTokenServerUri(URI.create(firebaseProperties.getTokenUri()))
                                .build()
                )
                .build();
        return FirebaseApp.initializeApp(options);
    }

    private String decodeBase64(String encoded) {
        return new String(Base64.getDecoder().decode(encoded));
    }

    /**
     * firebaseApp 초기화 이후에 접근해야함
     */
    @ConditionalOnMissingBean(FirebaseMessaging.class)
    @Bean
    public FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }

    @ConditionalOnMissingBean(NotificationService.class)
    @Bean
    public NotificationService notificationService(FirebaseMessaging firebaseMessaging) {
        return new FirebaseNotificationService(firebaseMessaging);
    }
}

