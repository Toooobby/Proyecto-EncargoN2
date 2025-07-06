package com.microservice.teacher.controller;

import com.microservice.teacher.assembler.TeacherDTOAssembler;
import com.microservice.teacher.dto.TeacherDTO;
import com.microservice.teacher.dto.TeacherResponseDTO;
import com.microservice.teacher.model.Teacher;
import com.microservice.teacher.service.TeacherService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping(value = "/api/v2/teacher", produces = MediaTypes.HAL_JSON_VALUE)
public class TeacherControllerV2 {

    private final TeacherService service;
    private final TeacherDTOAssembler assembler;

    public TeacherControllerV2(TeacherService service, TeacherDTOAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<TeacherResponseDTO>> listar() {
        List<TeacherDTO> dtos = service.listar().stream()
                .map(teacher -> service.getStudentWithCourse(teacher.getId()))
                .collect(Collectors.toList());

        List<EntityModel<TeacherResponseDTO>> models = dtos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                models,
                linkTo(methodOn(TeacherControllerV2.class).listar()).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<TeacherResponseDTO>> detalle(@PathVariable Long id) {
        TeacherDTO dto = service.getStudentWithCourse(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(dto));
    }

    @PostMapping
    public ResponseEntity<Teacher> crear(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(service.guardar(teacher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public CollectionModel<EntityModel<TeacherResponseDTO>> buscarPorNombre(@PathVariable String nombre) {
        List<TeacherDTO> dtos = service.buscarPorNombre(nombre).stream()
                .map(t -> service.getStudentWithCourse(t.getId()))
                .collect(Collectors.toList());

        List<EntityModel<TeacherResponseDTO>> models = dtos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                models,
                linkTo(methodOn(TeacherControllerV2.class).buscarPorNombre(nombre)).withSelfRel()
        );
    }

    @GetMapping("/buscar/course/{courseId}")
    public CollectionModel<EntityModel<TeacherResponseDTO>> buscarPorCourseId(@PathVariable Long courseId) {
        List<TeacherDTO> dtos = service.buscarPorCourseId(courseId).stream()
                .map(t -> service.getStudentWithCourse(t.getId()))
                .collect(Collectors.toList());

        List<EntityModel<TeacherResponseDTO>> models = dtos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                models,
                linkTo(methodOn(TeacherControllerV2.class).buscarPorCourseId(courseId)).withSelfRel()
        );
    }

    @GetMapping("/buscar")
    public CollectionModel<EntityModel<TeacherResponseDTO>> buscarPorNombreYCourseId(
            @RequestParam String nombre,
            @RequestParam Long courseId) {

        List<TeacherDTO> dtos = service.buscarPorNombreYCourseId(nombre, courseId).stream()
                .map(t -> service.getStudentWithCourse(t.getId()))
                .collect(Collectors.toList());

        List<EntityModel<TeacherResponseDTO>> models = dtos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                models,
                linkTo(methodOn(TeacherControllerV2.class).buscarPorNombreYCourseId(nombre, courseId)).withSelfRel()
        );
    }

    public Class<?> getStudentWithCourse(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'getStudentWithCourse'");
    }
}
