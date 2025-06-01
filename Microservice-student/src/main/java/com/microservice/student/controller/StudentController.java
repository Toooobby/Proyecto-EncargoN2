package com.microservice.student.controller;

import com.microservice.student.model.Student;
import com.microservice.student.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> detalle(@PathVariable Long id) {
        return service.porId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Student crear(@RequestBody Student student) {
        return service.guardar(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Opcional: búsqueda por nombre (ejemplo)
    @GetMapping("/buscar/nombre/{nombre}")
    public List<Student> buscarPorNombre(@PathVariable String nombre) {
        return service.buscarPorNombre(nombre);
    }

    // Opcional: búsqueda por curso
    @GetMapping("/buscar/curso/{curso}")
    public List<Student> buscarPorCurso(@PathVariable String curso) {
        return service.buscarPorCurso(curso);
    }

    // Opcional: búsqueda por nombre y curso
    @GetMapping("/buscar")
    public List<Student> buscarPorNombreYCurso(@RequestParam String nombre, @RequestParam String curso) {
        return service.buscarPorNombreYCurso(nombre, curso);
    }
}
