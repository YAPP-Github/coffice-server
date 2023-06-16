package kr.co.yapp._22nd.coffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CofficeApiApplication {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application,api");
        SpringApplication.run(CofficeApiApplication.class, args);
    }
}
