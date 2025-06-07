package com.microservice.course.service;

import com.microservice.course.model.Course;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> listar();
    Optional<Course> porId(Long id);
    Course guardar(Course course);
    void eliminar(Long id);
}
