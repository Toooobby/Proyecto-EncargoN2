package com.microservice.classroom.controller;

import com.microservice.classroom.assembler.ClassroomDTOAssembler;
import com.microservice.classroom.dto.ClassroomDTO;
import com.microservice.classroom.service.ClassroomService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping(value = "/api/v2/classroom", produces = MediaTypes.HAL_JSON_VALUE)
public class ClassroomControllerV2 {

    private final ClassroomService service;
    private final ClassroomDTOAssembler assembler;

    public ClassroomControllerV2(ClassroomService service, ClassroomDTOAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<ClassroomDTO>> listar() {
        List<ClassroomDTO> list = service.listar().stream()
                .map(c -> service.getClassroomWithCourse(c.getId()))
                .collect(Collectors.toList());

        List<EntityModel<ClassroomDTO>> resources = list.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(resources,
                linkTo(methodOn(ClassroomControllerV2.class).listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ClassroomDTO>> getClassroomWithCourse(@PathVariable Long id) {
        ClassroomDTO dto = service.getClassroomWithCourse(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(assembler.toModel(dto));
    }

    public Class<?> detalle(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'detalle'");
    }
}
