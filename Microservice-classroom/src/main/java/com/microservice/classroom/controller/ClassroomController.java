package com.microservice.classroom.controller;

import com.microservice.classroom.dto.ClassroomDTO;
import com.microservice.classroom.model.Classroom;
import com.microservice.classroom.service.ClassroomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classroom")
public class ClassroomController {

    private final ClassroomService service;

    public ClassroomController(ClassroomService service) {
        this.service = service;
    }

    @GetMapping
    public List<Classroom> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classroom> detalle(@PathVariable Long id) {
        return service.porId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Único método para obtener estudiante con curso
    @GetMapping("/with-course/{id}")
    public ResponseEntity<ClassroomDTO> getClassroomWithCourse(@PathVariable("id") Long id) {
        ClassroomDTO classroomDTO = service.getClassroomWithCourse(id);
        if (classroomDTO != null) {
            return ResponseEntity.ok(classroomDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Classroom crear(@RequestBody Classroom classroom) {
        return service.guardar(classroom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/numero/{numero}")
    public List<Classroom> buscarPorNumero(@PathVariable String numero) {
        return service.buscarPorNumero(numero);
    }

    @GetMapping("/buscar/course/{courseId}")
    public List<Classroom> buscarPorCourseId(@PathVariable Long courseId) {
        return service.buscarPorCourseId(courseId);
    }

    @GetMapping("/buscar")
    public List<Classroom> buscarPorNumeroYCourseId(
            @RequestParam String numero,
            @RequestParam Long courseId) {
        return service.buscarPorNumeroYCourseId(numero, courseId);
    }
}
