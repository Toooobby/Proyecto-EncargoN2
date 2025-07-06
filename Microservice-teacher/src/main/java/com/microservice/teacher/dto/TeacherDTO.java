package com.microservice.teacher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TeacherDTO {
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private CourseDTO course;
    public void setCourseId(Long courseId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCourseId'");
    }
}