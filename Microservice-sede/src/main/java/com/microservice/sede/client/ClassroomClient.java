package com.microservice.sede.client;

import com.microservice.sede.dto.ClassroomDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-classroom")
public interface ClassroomClient {

    @GetMapping("/api/classroom/{id}")
    ClassroomDTO getClassroomById(@PathVariable("id") Long id);

    // Puedes agregar más métodos si el microservicio curso tiene más endpoints que necesites.
}
