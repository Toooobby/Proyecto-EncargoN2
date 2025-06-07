package com.microservice.sede.controller;

import com.microservice.sede.dto.ClassroomDTO;
import com.microservice.sede.model.Sede;
import com.microservice.sede.service.SedeService;
import com.microservice.sede.client.ClassroomClient;  // Asegúrate que tienes este FeignClient
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sede")
public class SedeController {

    private final SedeService service;
    private final ClassroomClient classroomClient;

    // Inyección por constructor (recomendada)
    public SedeController(SedeService service, ClassroomClient classroomClient) {
        this.service = service;
        this.classroomClient = classroomClient;
    }

    @GetMapping
    public List<Sede> listAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sede> findById(@PathVariable Long id) {
    return service.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/{id}/classroom")
    public ResponseEntity<?> obtenerSedeConClassrooms(@PathVariable Long id) {
        return service.findById(id)
                .map(sede -> {
                    List<ClassroomDTO> classrooms = classroomClient.findBySedeId(sede.getId());
                    return ResponseEntity.ok().body(new Object() {
                        public final Sede sedeInfo = sede;
                        public final List<ClassroomDTO> classroomsInfo = classrooms;
                    });
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sede save(@RequestBody Sede sede) {
        return service.save(sede);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
