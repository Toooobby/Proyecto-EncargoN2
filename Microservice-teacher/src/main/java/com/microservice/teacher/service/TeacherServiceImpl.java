package com.microservice.teacher.service;

import com.microservice.teacher.client.CourseClient;
import com.microservice.teacher.dto.CourseDTO;
import com.microservice.teacher.dto.TeacherDTO;
import com.microservice.teacher.model.Teacher;
import com.microservice.teacher.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository repository;
    private final CourseClient courseClient;

    public TeacherServiceImpl(TeacherRepository repository, CourseClient courseClient) {
        this.repository = repository;
        this.courseClient = courseClient;
    }

    @Override
    public List<Teacher> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Teacher> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Teacher guardar(Teacher student) {
        return repository.save(student);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Teacher> buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Override
    public List<Teacher> buscarPorCourseId(Long courseId) {
        return repository.findByCourseId(courseId);
    }

    @Override
    public List<Teacher> buscarPorNombreYCourseId(String nombre, Long courseId) {
        return repository.findByNombreAndCourseId(nombre, courseId);
    }

    // Nuevo método para devolver estudiante con info de curso
    @Override
    public TeacherDTO getStudentWithCourse(Long id) {
        Optional<Teacher> optionalStudent = repository.findById(id);

        if (optionalStudent.isPresent()) {
            Teacher student = optionalStudent.get();

            CourseDTO course = null;
            if (student.getCourseId() != null) {
                course = courseClient.getCourseById(student.getCourseId());
            }

            return new TeacherDTO(
                    student.getId(),
                    student.getNombre(),
                    student.getApellido(),
                    student.getEmail(),
                    course
            );
        }

        return null;
    }

}
