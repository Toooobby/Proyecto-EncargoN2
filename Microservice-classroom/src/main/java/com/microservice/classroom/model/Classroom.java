package com.microservice.classroom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;        // número o código de aula
    private String ubicacion;     // descripción de la ubicación
    private int capacidad;        // capacidad de personas

    private Long sedeId;          // referencia a sede
    private Long courseId;        // referencia a curso
}
