package com.microservice.teacher.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TeacherResponseDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private CourseDTO course;

    public TeacherResponseDTO(Long id, String nombre, String apellido, String email, CourseDTO course) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.course = course;
    }
}
