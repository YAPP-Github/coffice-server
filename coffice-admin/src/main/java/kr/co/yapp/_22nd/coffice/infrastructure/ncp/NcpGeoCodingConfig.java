package kr.co.yapp._22nd.coffice.infrastructure.ncp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class NcpGeoCodingConfig {
    @Value("${coffice.ncp.api-key-id}")
    private String ncpApiKeyId;
    @Value("${coffice.ncp.api-key}")
    private String ncpApiKey;

    @Bean("ncpGeoCodingRestTemplate")
    public RestTemplate ncpGeoCodingRestTemplate() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(1))
                .setReadTimeout(Duration.ofSeconds(3))
                .additionalInterceptors(new NcpGeoCodingInterceptor(ncpApiKeyId, ncpApiKey))
                .build();
    }

}
