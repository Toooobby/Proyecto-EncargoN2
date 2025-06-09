package com.microservice.teacher.repository;

import com.microservice.teacher.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByNombre(String nombre);

    // Cambiado a courseId de tipo Long
    List<Teacher> findByCourseId(Long courseId);

    List<Teacher> findByNombreAndCourseId(String nombre, Long courseId);
}
