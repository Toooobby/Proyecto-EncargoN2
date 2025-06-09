package com.microservice.classroom.repository;

import com.microservice.classroom.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    List<Classroom> findByNumero(String numero);
    List<Classroom> findByCourseId(Long courseId);
    List<Classroom> findByNumeroAndCourseId(String numero, Long courseId);
}
