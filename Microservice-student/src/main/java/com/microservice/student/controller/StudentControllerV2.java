package com.microservice.student.controller;

import com.microservice.student.assembler.StudentDTOAssembler; // Cambiar esta importación
import com.microservice.student.dto.StudentDTO;
import com.microservice.student.dto.StudentResponseDTO;
import com.microservice.student.model.Student;
import com.microservice.student.service.StudentService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/student")
public class StudentControllerV2 {

    private final StudentService service;
    private final StudentDTOAssembler assembler; // Cambiar a StudentDTOAssembler

    public StudentControllerV2(StudentService service, StudentDTOAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    // Resto del código permanece igual
    @GetMapping
    public CollectionModel<EntityModel<StudentResponseDTO>> listar() {
        List<Student> students = service.listar();
        List<EntityModel<StudentResponseDTO>> studentModels = students.stream()
                .map(student -> assembler.toModel(service.getStudentWithCourse(student.getId())))
                .collect(Collectors.toList());

        return CollectionModel.of(
                studentModels,
                linkTo(methodOn(StudentControllerV2.class).listar()).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentResponseDTO>> detalle(@PathVariable Long id) {
        StudentDTO studentDTO = service.getStudentWithCourse(id);
        if (studentDTO == null) {
            return ResponseEntity.notFound().build();
        }
        EntityModel<StudentResponseDTO> model = assembler.toModel(studentDTO);
        return ResponseEntity.ok(model);
    }

    @PostMapping
    public ResponseEntity<Student> crear(@RequestBody Student student) {
        Student creado = service.guardar(student);
        return ResponseEntity.ok(creado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public CollectionModel<EntityModel<StudentResponseDTO>> buscarPorNombre(@PathVariable String nombre) {
        List<Student> students = service.buscarPorNombre(nombre);
        List<EntityModel<StudentResponseDTO>> models = students.stream()
                .map(student -> assembler.toModel(service.getStudentWithCourse(student.getId())))
                .collect(Collectors.toList());

        return CollectionModel.of(
                models,
                linkTo(methodOn(StudentControllerV2.class).buscarPorNombre(nombre)).withSelfRel()
        );
    }

    @GetMapping("/buscar/course/{courseId}")
    public CollectionModel<EntityModel<StudentResponseDTO>> buscarPorCourseId(@PathVariable Long courseId) {
        List<Student> students = service.buscarPorCourseId(courseId);
        List<EntityModel<StudentResponseDTO>> models = students.stream()
                .map(student -> assembler.toModel(service.getStudentWithCourse(student.getId())))
                .collect(Collectors.toList());

        return CollectionModel.of(
                models,
                linkTo(methodOn(StudentControllerV2.class).buscarPorCourseId(courseId)).withSelfRel()
        );
    }

    @GetMapping("/buscar")
    public CollectionModel<EntityModel<StudentResponseDTO>> buscarPorNombreYCourseId(
            @RequestParam String nombre,
            @RequestParam Long courseId) {
        List<Student> students = service.buscarPorNombreYCourseId(nombre, courseId);
        List<EntityModel<StudentResponseDTO>> models = students.stream()
                .map(student -> assembler.toModel(service.getStudentWithCourse(student.getId())))
                .collect(Collectors.toList());

        return CollectionModel.of(
                models,
                linkTo(methodOn(StudentControllerV2.class).buscarPorNombreYCourseId(nombre, courseId)).withSelfRel()
        );
    }
}