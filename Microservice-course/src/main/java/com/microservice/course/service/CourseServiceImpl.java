package com.microservice.course.service;

import com.microservice.course.model.Course;
import com.microservice.course.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    public CourseServiceImpl(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Course> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Course> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Course guardar(Course course) {
        return repository.save(course);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
