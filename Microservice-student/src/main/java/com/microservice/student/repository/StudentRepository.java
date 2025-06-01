package com.microservice.student.repository;

import com.microservice.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNombre(String nombre);
    List<Student> findByCurso(String curso);
    List<Student> findByNombreAndCurso(String nombre, String curso);
}
