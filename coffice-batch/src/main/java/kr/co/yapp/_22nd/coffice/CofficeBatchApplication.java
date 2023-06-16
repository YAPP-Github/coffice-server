package kr.co.yapp._22nd.coffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CofficeBatchApplication {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application,batch");
        SpringApplication.run(CofficeBatchApplication.class, args);
    }
}
