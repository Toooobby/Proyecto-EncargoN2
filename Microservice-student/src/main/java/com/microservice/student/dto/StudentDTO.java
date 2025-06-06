package com.microservice.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private CourseDTO course;  // aquí va el curso completo
}
