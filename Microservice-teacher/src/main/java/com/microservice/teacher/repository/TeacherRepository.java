package com.microservice.teacher.repository;

import com.microservice.teacher.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // No se necesita método extra para listar todo porque JpaRepository ya tiene findAll()
}
