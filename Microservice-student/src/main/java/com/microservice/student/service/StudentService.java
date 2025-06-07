package com.microservice.student.service;

import com.microservice.student.dto.StudentDTO;
import com.microservice.student.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentDTO getStudentWithCourse(Long id);

    List<Student> listar();

    Optional<Student> porId(Long id);

    Student guardar(Student student);

    void eliminar(Long id);

    List<Student> buscarPorNombre(String nombre);

    // Cambiado a Long courseId
    List<Student> buscarPorCourseId(Long courseId);

    List<Student> buscarPorNombreYCourseId(String nombre, Long courseId);
}
