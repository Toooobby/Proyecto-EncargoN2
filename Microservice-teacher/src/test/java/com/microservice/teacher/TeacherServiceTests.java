package com.microservice.teacher;

import com.microservice.teacher.client.CourseClient;
import com.microservice.teacher.model.Teacher;
import com.microservice.teacher.repository.TeacherRepository;
import com.microservice.teacher.service.TeacherServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTests {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private CourseClient courseClient;

    @InjectMocks
    private TeacherServiceImpl teacherService;

    @Test
    public void listar_deberiaRetornarListaDeTeachers() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setNombre("María");
        teacher.setApellido("López");
        teacher.setEmail("maria.lopez@mail.com");
        teacher.setCourseId(1L);

        Mockito.when(teacherRepository.findAll()).thenReturn(List.of(teacher));

        List<Teacher> result = teacherService.listar();

        assertFalse(result.isEmpty());
        assertEquals("María", result.get(0).getNombre());
    }

    @Test
    public void porId_deberiaRetornarTeacherExistente() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setNombre("José");
        teacher.setApellido("Martínez");
        teacher.setEmail("jose@mail.com");
        teacher.setCourseId(2L);

        Mockito.when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));

        Optional<Teacher> result = teacherService.porId(1L);

        assertTrue(result.isPresent());
        assertEquals("José", result.get().getNombre());
    }
}
