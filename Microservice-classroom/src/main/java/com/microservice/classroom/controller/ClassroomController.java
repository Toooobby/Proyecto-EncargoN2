package com.microservice.classroom.controller;

import com.microservice.classroom.model.Classroom;
import com.microservice.classroom.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classroom")
public class ClassroomController {

    @Autowired
    private ClassroomService service;

    @GetMapping
    public List<Classroom> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Classroom> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Classroom save(@RequestBody Classroom classroom) {
        return service.save(classroom);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
