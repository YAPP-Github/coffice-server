package kr.co.yapp._22nd.coffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CofficeConsumerApplication {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application,consumer");
        SpringApplication.run(CofficeConsumerApplication.class, args);
    }
}
