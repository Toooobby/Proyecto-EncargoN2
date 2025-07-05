package com.microservice.classroom;

import com.microservice.classroom.controller.ClassroomController;
import com.microservice.classroom.model.Classroom;
import com.microservice.classroom.service.ClassroomService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClassroomController.class)
public class ClassroomControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClassroomService classroomService;

    @Test
    public void listar_deberiaRetornarListaDeAulas() throws Exception {
        Classroom aula = new Classroom();
        aula.setId(1L);
        aula.setNumero("101");
        aula.setCourseId(1L);

        Mockito.when(classroomService.listar()).thenReturn(List.of(aula));

        mockMvc.perform(get("/api/classroom")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].numero", is("101")));
    }

    @Test
    public void detalle_deberiaRetornar404CuandoNoExiste() throws Exception {
        Mockito.when(classroomService.porId(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/classroom/999")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
}
