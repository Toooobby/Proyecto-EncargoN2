package com.microservice.sede.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservice.sede.model.Sede;
import com.microservice.sede.repository.SedeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SedeService {

    @Autowired
    private SedeRepository repository;

    @Override
    public List<Sede> findAll(){
        return repository.findAll();
    }

    @Override
    public Optional<Sede> findByCodigo(Long id){
        return repository.findByCodigo(id);
    }
}
