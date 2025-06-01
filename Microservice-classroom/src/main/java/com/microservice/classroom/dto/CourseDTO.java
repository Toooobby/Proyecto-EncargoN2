package com.microservice.classroom.dto;

import lombok.Data;

@Data
public class CourseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Long teacherId;
}
