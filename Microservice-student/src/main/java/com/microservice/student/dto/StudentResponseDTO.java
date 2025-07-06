package com.microservice.student.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class StudentResponseDTO {
    @Schema(description = "ID del estudiante")
    private Long id;

    @Schema(description = "Nombre del estudiante")
    private String nombre;

    @Schema(description = "Apellido del estudiante")
    private String apellido;

    @Schema(description = "Correo electrónico del estudiante")
    private String email;

    @Schema(description = "Curso asociado al estudiante")
    private CourseDTO course;
}