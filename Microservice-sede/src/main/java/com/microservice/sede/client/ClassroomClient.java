package com.microservice.sede.client;

import com.microservice.sede.dto.ClassroomDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-classroom")
public interface ClassroomClient {

    @GetMapping("/api/classroom/sede/{sedeId}")
    List<ClassroomDTO> findBySedeId(@PathVariable Long sedeId);
}
