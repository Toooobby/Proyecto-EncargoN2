package com.microservice.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.microservice.course.client")
public class MicroserviceCourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceCourseApplication.class, args);
    }
}
