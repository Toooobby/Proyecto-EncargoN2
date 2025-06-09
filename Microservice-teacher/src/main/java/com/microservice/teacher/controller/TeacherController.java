package com.microservice.teacher.controller;

import com.microservice.teacher.dto.TeacherDTO;
import com.microservice.teacher.model.Teacher;
import com.microservice.teacher.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

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

    // ✅ Único método para obtener estudiante con curso
    @GetMapping("/with-course/{id}")
    public ResponseEntity<TeacherDTO> getStudentWithCourse(@PathVariable("id") Long id) {
        TeacherDTO studentDTO = service.getStudentWithCourse(id);
        if (studentDTO != null) {
            return ResponseEntity.ok(studentDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Teacher crear(@RequestBody Teacher student) {
        return service.guardar(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public List<Teacher> buscarPorNombre(@PathVariable String nombre) {
        return service.buscarPorNombre(nombre);
    }

    @GetMapping("/buscar/course/{courseId}")
    public List<Teacher> buscarPorCourseId(@PathVariable Long courseId) {
        return service.buscarPorCourseId(courseId);
    }

    @GetMapping("/buscar")
    public List<Teacher> buscarPorNombreYCourseId(
            @RequestParam String nombre,
            @RequestParam Long courseId) {
        return service.buscarPorNombreYCourseId(nombre, courseId);
    }
}
