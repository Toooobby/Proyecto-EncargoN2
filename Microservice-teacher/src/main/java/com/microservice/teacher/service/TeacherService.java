package com.microservice.teacher.service;

import com.microservice.teacher.model.Teacher;
import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> listar();
    Optional<Teacher> porId(Long id);
    Teacher guardar(Teacher teacher);
    void eliminar(Long id);
}
