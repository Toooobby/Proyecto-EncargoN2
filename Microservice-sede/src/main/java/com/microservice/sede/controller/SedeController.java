package com.microservice.sede.controller;

import com.microservice.sede.model.Sede;
import com.microservice.sede.service.SedeService;
import com.netflix.discovery.converters.Auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/sede")
public class SedeController {

    @Autowired
    private SedeService Service;

    @GetMapping("/{id}")
    public Optional<Sede> findbyCodigo(@PathVariable Long id){
        return Service.findbyCodigo(id);
    }
    }
    
}
