package com.microservice.course.controller;

import com.microservice.course.model.Course;
import com.microservice.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping
    public List<Course> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Course> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Course save(@RequestBody Course course) {
        return service.save(course);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
