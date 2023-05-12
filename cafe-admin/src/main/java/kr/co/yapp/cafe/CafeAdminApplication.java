package kr.co.yapp.cafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CafeAdminApplication {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application,admin");
        SpringApplication.run(CafeAdminApplication.class, args);
    }
}
