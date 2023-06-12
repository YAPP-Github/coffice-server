package kr.co.yapp.cafe.notification.firebase;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("cafe.notification.firebase")
@Data
public class FirebaseProperties {
    private String projectId;
    private String privateKeyId;
    private String privateKey;
    private String clientEmail;
    private String clientId;
    private String tokenUri;
}
