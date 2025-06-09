package com.microservice.classroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomDTO {
    private Long id;
    private String numero;
    private String ubicacion;
    private int capacidad;

    // ✅ Añadir este campo si ya tienes CourseDTO importado correctamente
    private CourseDTO course;
}
