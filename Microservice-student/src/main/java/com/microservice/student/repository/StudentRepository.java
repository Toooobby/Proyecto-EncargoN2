package com.microservice.student.repository;

import com.microservice.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNombre(String nombre);

    // Cambiado a courseId de tipo Long
    List<Student> findByCourseId(Long courseId);

    List<Student> findByNombreAndCourseId(String nombre, Long courseId);
}
