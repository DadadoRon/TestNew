package org.example.testnew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TestNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestNewApplication.class, args);
    }

}
