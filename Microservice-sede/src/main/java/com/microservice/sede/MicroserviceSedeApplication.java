package com.microservice.sede;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceSedeApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceSedeApplication.class, args);
    }
}
