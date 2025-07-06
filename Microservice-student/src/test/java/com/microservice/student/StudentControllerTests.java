package com.microservice.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.student.controller.StudentController;
import com.microservice.student.dto.CourseDTO;
import com.microservice.student.dto.StudentDTO;
import com.microservice.student.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getStudentWithCourse_returnsOk() throws Exception {
        StudentDTO student = new StudentDTO(
                1L,
                "Ana",
                "Gómez",
                "ana.gomez@mail.com",
                new CourseDTO(10L, "Ingeniería", "Descripción del curso")
        );

        when(service.getStudentWithCourse(1L)).thenReturn(student);

        mockMvc.perform(get("/api/student/with-course/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ana"))
                .andExpect(jsonPath("$.course.nombre").value("Ingeniería"));
    }
}
