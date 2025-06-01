package com.microservice.course.dto;

import lombok.Data;

@Data
public class TeacherDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String curso;
}
