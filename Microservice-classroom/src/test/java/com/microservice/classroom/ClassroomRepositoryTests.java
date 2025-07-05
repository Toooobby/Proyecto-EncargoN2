package com.microservice.classroom;

import com.microservice.classroom.model.Classroom;
import com.microservice.classroom.repository.ClassroomRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test") // Usa application-test.yml
public class ClassroomRepositoryTests {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Test
    void guardar_yBuscarPorId_deberiaFuncionar() {
        Classroom aula = Classroom.builder()
            .numero("101")
            .ubicacion("Segundo piso")
            .capacidad(30)
            .courseId(1L)
            .build();

        Classroom saved = classroomRepository.save(aula);

        Optional<Classroom> found = classroomRepository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("101", found.get().getNumero());
    }
}
