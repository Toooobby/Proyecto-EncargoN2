package com.microservice.sede.repository;

import com.microservice.sede.model.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Long> {
    // No necesitas definir findById porque JpaRepository ya lo tiene.
}
