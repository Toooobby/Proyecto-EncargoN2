package com.microservice.teacher.service;

import com.microservice.teacher.dto.TeacherDTO;
import com.microservice.teacher.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    TeacherDTO getStudentWithCourse(Long id);

    List<Teacher> listar();

    Optional<Teacher> porId(Long id);

    Teacher guardar(Teacher student);

    void eliminar(Long id);

    List<Teacher> buscarPorNombre(String nombre);

    // Cambiado a Long courseId
    List<Teacher> buscarPorCourseId(Long courseId);

    List<Teacher> buscarPorNombreYCourseId(String nombre, Long courseId);
}
