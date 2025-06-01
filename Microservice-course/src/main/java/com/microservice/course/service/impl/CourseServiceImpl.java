package com.microservice.course.service.impl;

import com.microservice.course.model.Course;
import com.microservice.course.repository.CourseRepository;
import com.microservice.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository repository;

    @Override
    public List<Course> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Course> findByTeacherId(Long teacherId) {
    return CourseRepository.findByTeacherId(teacherId);
    }
    
    @Override
    public Optional<Course> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Course save(Course course) {
        return repository.save(course);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
