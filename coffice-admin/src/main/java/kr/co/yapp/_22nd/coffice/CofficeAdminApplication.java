package kr.co.yapp._22nd.coffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CofficeAdminApplication {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application,admin");
        SpringApplication.run(CofficeAdminApplication.class, args);
    }
}
