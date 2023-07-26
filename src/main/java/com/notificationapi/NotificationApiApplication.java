package com.notificationapi;

import com.notificationapi.enums.SmsType;
import com.notificationapi.model.sms.SmsTokenModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApiApplication.class, args);
    }

}
