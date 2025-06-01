package com.microservice.course.controller;

import com.microservice.course.client.TeacherClient;
import com.microservice.course.dto.CourseWithTeacherDTO;
import com.microservice.course.dto.TeacherDTO;
import com.microservice.course.model.Course;
import com.microservice.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    @Autowired
    private TeacherClient teacherClient;

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

    // Nuevo endpoint que devuelve curso con datos del profesor
    @GetMapping("/{id}/with-teacher")
    public CourseWithTeacherDTO getCourseWithTeacher(@PathVariable Long id) {
        Course course = service.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con id: " + id));
        TeacherDTO teacher = teacherClient.getTeacherById(course.getTeacherId());
        return new CourseWithTeacherDTO(course, teacher);
    }
}
