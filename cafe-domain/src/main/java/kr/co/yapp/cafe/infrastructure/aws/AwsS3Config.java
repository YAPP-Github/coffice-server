package kr.co.yapp.cafe.infrastructure.aws;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsS3Config {
    @Value("${cafe.aws.s3.access-key}")
    private String accessKey;
    @Value("${cafe.aws.s3.secret-key}")
    private String secretKey;
    @Value("${cafe.aws.s3.region}")
    private String region;
    @Value("${cafe.aws.s3.connection-timeout:1000}")
    private Integer connectionTimeout;
    @Value("${cafe.aws.s3.request-timeout:3000}")
    private Integer requestTimeout;

    @Bean
    public AmazonS3 amazonS3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setConnectionTimeout(connectionTimeout);
        clientConfiguration.setRequestTimeout(requestTimeout);
        return AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withClientConfiguration(clientConfiguration)
                .build();
    }
}
