package com.microservice.sede.service;

import com.microservice.sede.model.Sede;

import java.util.List;
import java.util.Optional;

public interface SedeService {
    List<Sede> findAll();
    Optional<Sede> findById(Long id);
    Sede save(Sede sede);
    void deleteById(Long id);
}
