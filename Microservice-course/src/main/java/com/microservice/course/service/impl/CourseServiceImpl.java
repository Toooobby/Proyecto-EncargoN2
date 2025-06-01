package com.microservice.course.service.impl;
import com.microservice.course.client.TeacherClient;
import com.microservice.course.dto.CourseWithTeacherDTO;
import com.microservice.course.dto.TeacherDTO;
import com.microservice.course.model.Course;
import com.microservice.course.repository.CourseRepository;
import com.microservice.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private TeacherClient teacherClient;

    @Override
    public List<Course> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
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

    @Override
    public Optional<CourseWithTeacherDTO> findCourseWithTeacherById(Long id) {
        Optional<Course> courseOpt = repository.findById(id);
        if (courseOpt.isEmpty()) {
            return Optional.empty();
        }

        Course course = courseOpt.get();

        // Llamar al microservicio teacher para obtener datos del profesor
        TeacherDTO teacher = teacherClient.getTeacherById(course.getTeacherId());

        CourseWithTeacherDTO courseWithTeacherDTO = new CourseWithTeacherDTO(course, teacher);
        return Optional.of(courseWithTeacherDTO);
    }
}
