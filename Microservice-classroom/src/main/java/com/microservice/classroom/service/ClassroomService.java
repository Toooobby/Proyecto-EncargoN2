package com.microservice.classroom.service;

import com.microservice.classroom.model.Classroom;
import java.util.List;
import java.util.Optional;

public interface ClassroomService {
    List<Classroom> findAll();
    Optional<Classroom> findById(Long id);
    Classroom save(Classroom classroom);
    void deleteById(Long id);
}
