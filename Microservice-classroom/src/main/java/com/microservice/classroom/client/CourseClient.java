package com.microservice.classroom.client;

import com.microservice.classroom.dto.CourseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-course")
public interface CourseClient {

    @GetMapping("/api/course/{id}")
    CourseDTO getCourseById(@PathVariable("id") Long id);

    // Puedes agregar más métodos si el microservicio curso tiene más endpoints que necesites.
}
