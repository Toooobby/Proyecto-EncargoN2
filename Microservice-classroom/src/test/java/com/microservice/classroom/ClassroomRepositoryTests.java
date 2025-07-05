package com.microservice.classroom;

import com.microservice.classroom.model.Classroom;
import com.microservice.classroom.repository.ClassroomRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ClassroomRepositoryTests {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Test
    public void guardar_yBuscarPorId_deberiaFuncionar() {
        Classroom aula = new Classroom();
        aula.setNumero("101");
        aula.setCourseId(1L);

        Classroom saved = classroomRepository.save(aula);

        Optional<Classroom> found = classroomRepository.findById(saved.getId());

        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals("101", found.get().getNumero());
    }
}
