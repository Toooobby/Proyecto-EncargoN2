package com.microservice.student.service;

import com.microservice.student.client.CourseClient;
import com.microservice.student.dto.CourseDTO;
import com.microservice.student.dto.StudentDTO;
import com.microservice.student.model.Student;
import com.microservice.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final CourseClient courseClient;

    public StudentServiceImpl(StudentRepository repository, CourseClient courseClient) {
        this.repository = repository;
        this.courseClient = courseClient;
    }

    @Override
    public List<Student> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Student> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Student guardar(Student student) {
        return repository.save(student);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Student> buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Override
    public List<Student> buscarPorCourseId(Long courseId) {
        return repository.findByCourseId(courseId);
    }

    @Override
    public List<Student> buscarPorNombreYCourseId(String nombre, Long courseId) {
        return repository.findByNombreAndCourseId(nombre, courseId);
    }

    // Nuevo método para devolver estudiante con info de curso
    @Override
    public StudentDTO getStudentWithCourse(Long id) {
        Optional<Student> optionalStudent = repository.findById(id);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            CourseDTO course = null;
            if (student.getCourseId() != null) {
                course = courseClient.getCourseById(student.getCourseId());
            }

            return new StudentDTO(
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
