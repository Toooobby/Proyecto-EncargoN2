package com.microservice.classroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceClassroomApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceClassroomApplication.class, args);
    }
}

