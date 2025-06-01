package com.microservice.student.service;

import com.microservice.student.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> listar();
    Optional<Student> porId(Long id);
    Student guardar(Student student);
    void eliminar(Long id);
    List<Student> buscarPorNombre(String nombre);
    List<Student> buscarPorCurso(String curso);
    List<Student> buscarPorNombreYCurso(String nombre, String curso);
}
