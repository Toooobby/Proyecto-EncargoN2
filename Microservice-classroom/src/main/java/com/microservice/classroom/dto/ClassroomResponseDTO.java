package com.microservice.classroom.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ClassroomResponseDTO {
    private Long id;
    private String numero;
    private String ubicacion;
    private int capacidad;
    private CourseDTO course;

    @Schema(description = "Enlaces HATEOAS")
    private Links _links;

    @Data
    public static class Links {
        private Link self;
        private Link classrooms;
        private Link course;

        @Data
        public static class Link {
            private String href;
        }
    }
}
