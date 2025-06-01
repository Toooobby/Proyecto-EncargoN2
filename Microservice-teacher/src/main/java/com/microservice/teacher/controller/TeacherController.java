package com.microservice.teacher.controller;

import com.microservice.teacher.model.Teacher;
import com.microservice.teacher.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    // Endpoint para listar todos los profesores
    @GetMapping
    public List<Teacher> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> detalle(@PathVariable Long id) {
        return service.porId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Teacher crear(@RequestBody Teacher teacher) {
        return service.guardar(teacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
