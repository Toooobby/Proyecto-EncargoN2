package com.microservice.sede.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SedeDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private String ciudad;

    private ClassroomDTO classroom;
}
