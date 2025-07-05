package com.microservice.sede.service;

import com.microservice.sede.dto.SedeDTO;
import com.microservice.sede.model.Sede;

import java.util.List;
import java.util.Optional;

public interface SedeService {

    // Método para retornar una sede con información de aula (usando Feign)
    SedeDTO getSedeWithClassroom(Long id);

    // Métodos estándar
    List<Sede> listar();
    Optional<Sede> porId(Long id);
    Sede guardar(Sede sede);
    void eliminar(Long id);

    // Búsqueda por nombre, si la entidad Sede tiene ese campo
    List<Sede> buscarPorNombre(String nombre);
}