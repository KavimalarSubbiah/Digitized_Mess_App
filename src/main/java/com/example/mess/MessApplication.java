package com.example.mess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MessApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessApplication.class, args);
    }

}
