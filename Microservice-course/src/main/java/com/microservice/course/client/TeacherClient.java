package com.microservice.course.client;

import com.microservice.course.dto.TeacherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-teacher")  // nombre del microservicio registrado en Eureka
public interface TeacherClient {

    @GetMapping("/api/v1/teacher/{id}")
    TeacherDTO getTeacherById(@PathVariable("id") Long id);
}
