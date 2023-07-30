package com.notificationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class NotificationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApiApplication.class, args);
    }
}
