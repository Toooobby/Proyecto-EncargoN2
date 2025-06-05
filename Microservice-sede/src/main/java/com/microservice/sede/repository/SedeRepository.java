package com.microservice.sede.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface SedeRepository extends JpaRepository<sede, Long> {
    List<sede> findByCodigoSede(String codigoSede);
}
