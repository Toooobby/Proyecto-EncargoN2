package com.microservice.sede.service.impl;

import com.microservice.sede.model.Sede;
import com.microservice.sede.repository.SedeRepository;
import com.microservice.sede.service.SedeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SedeServiceImpl implements SedeService {

    private final SedeRepository sedeRepository;

    public SedeServiceImpl(SedeRepository sedeRepository) {
        this.sedeRepository = sedeRepository;
    }

    @Override
    public List<Sede> findAll() {
        return sedeRepository.findAll();
    }

    @Override
    public Optional<Sede> findById(Long id) {
        return sedeRepository.findById(id);
    }

    @Override
    public Sede save(Sede sede) {
        return sedeRepository.save(sede);
    }

    @Override
    public void deleteById(Long id) {
        sedeRepository.deleteById(id);
    }
}
