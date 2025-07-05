package com.microservice.sede.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SedeResponseDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private String ciudad;
    private ClassroomDTO classroom;

    @Schema(description = "Enlaces HATEOAS")
    private Links _links;

    @Data
    public static class Links {
        private Link self;
        private Link sedes;
        private Link classroom;

        @Data
        public static class Link {
            private String href;
        }
    }
}
