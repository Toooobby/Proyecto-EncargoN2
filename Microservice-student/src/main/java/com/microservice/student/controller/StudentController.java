package com.microservice.student.controller;

import com.microservice.student.dto.StudentDTO;
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

    // ✅ Único método para obtener estudiante con curso
    @GetMapping("/with-course/{id}")
    public ResponseEntity<StudentDTO> getStudentWithCourse(@PathVariable("id") Long id) {
        StudentDTO studentDTO = service.getStudentWithCourse(id);
        if (studentDTO != null) {
            return ResponseEntity.ok(studentDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
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

    @GetMapping("/buscar/nombre/{nombre}")
    public List<Student> buscarPorNombre(@PathVariable String nombre) {
        return service.buscarPorNombre(nombre);
    }

    @GetMapping("/buscar/course/{courseId}")
    public List<Student> buscarPorCourseId(@PathVariable Long courseId) {
        return service.buscarPorCourseId(courseId);
    }

    @GetMapping("/buscar")
    public List<Student> buscarPorNombreYCourseId(
            @RequestParam String nombre,
            @RequestParam Long courseId) {
        return service.buscarPorNombreYCourseId(nombre, courseId);
    }
}
