package com.microservice.sede.repository;

import com.microservice.sede.model.Sede;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SedeRepository extends JpaRepository<Sede, Long> {
    List<Sede> findByNombre(String nombre);

    List<Sede> findByClassroomId(Long classroomId); // ✅ nombre correcto

    List<Sede> findByNombreAndClassroomId(String nombre, Long classroomId); // ✅ nombre correcto
}
