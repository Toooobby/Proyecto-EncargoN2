package com.microservice.classroom.service;

import com.microservice.classroom.dto.ClassroomDTO;
import com.microservice.classroom.model.Classroom;

import java.util.List;
import java.util.Optional;

public interface ClassroomService {

    ClassroomDTO getClassroomWithCourse(Long id);

    List<Classroom> listar();
    Optional<Classroom> porId(Long id);

    Classroom guardar(Classroom classroom);

    void eliminar(Long id);

    List<Classroom> buscarPorNumero(String numero);

    // Cambiado a Long courseId
    List<Classroom> buscarPorCourseId(Long courseId);

    List<Classroom> buscarPorNumeroYCourseId(String numero, Long courseId);
}
