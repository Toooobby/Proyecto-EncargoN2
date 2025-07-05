package com.microservice.classroom;

import com.microservice.classroom.model.Classroom;
import com.microservice.classroom.repository.ClassroomRepository;
import com.microservice.classroom.client.CourseClient;
import com.microservice.classroom.service.ClassroomServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClassroomServiceTests {

    @Mock
    private ClassroomRepository classroomRepository;

    @Mock
    private CourseClient courseClient;  // Mock también la otra dependencia

    @InjectMocks
    private ClassroomServiceImpl classroomService; // Implementación concreta

    @Test
    public void listar_deberiaRetornarListaDeAulas() {
        Classroom aula = new Classroom();
        aula.setId(1L);
        aula.setNumero("101");
        aula.setCourseId(1L);

        Mockito.when(classroomRepository.findAll()).thenReturn(List.of(aula));

        List<Classroom> result = classroomService.listar();

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals("101", result.get(0).getNumero());
    }

    @Test
    public void porId_deberiaRetornarAulaExistente() {
        Classroom aula = new Classroom();
        aula.setId(1L);
        aula.setNumero("101");
        aula.setCourseId(1L);

        Mockito.when(classroomRepository.findById(1L)).thenReturn(Optional.of(aula));

        Optional<Classroom> result = classroomService.porId(1L);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("101", result.get().getNumero());
    }
}
