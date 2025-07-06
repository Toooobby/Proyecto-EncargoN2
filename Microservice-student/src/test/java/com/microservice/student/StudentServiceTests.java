package com.microservice.student;

import com.microservice.student.client.CourseClient;
import com.microservice.student.dto.CourseDTO;
import com.microservice.student.dto.StudentDTO;
import com.microservice.student.model.Student;
import com.microservice.student.repository.StudentRepository;
import com.microservice.student.service.StudentServiceImpl;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class StudentServiceTests {

    private StudentServiceImpl service;
    private StudentRepository repository;
    private CourseClient courseClient;
    private Faker faker;

    @BeforeEach
    void setUp() {
        repository = mock(StudentRepository.class);
        courseClient = mock(CourseClient.class);
        service = new StudentServiceImpl(repository, courseClient);
        faker = new Faker();
    }

    @Test
    void getStudentWithCourse_returnsStudentDTO() {
        Student student = Student.builder()
                .id(1L)
                .nombre(faker.name().firstName())
                .apellido(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .courseId(100L)
                .build();

        CourseDTO courseDTO = new CourseDTO(100L, "Arquitectura de software", "Curso avanzado");

        when(repository.findById(1L)).thenReturn(Optional.of(student));
        when(courseClient.getCourseById(100L)).thenReturn(courseDTO);

        StudentDTO result = service.getStudentWithCourse(1L);

        assertThat(result).isNotNull();
        assertThat(result.getCourse().getNombre()).isEqualTo("Arquitectura de software");
        verify(repository).findById(1L);
        verify(courseClient).getCourseById(100L);
    }
}
