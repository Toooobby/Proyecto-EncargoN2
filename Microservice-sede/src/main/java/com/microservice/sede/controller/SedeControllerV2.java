package com.microservice.sede.controller;

import com.microservice.sede.assembler.SedeDTOAssembler;
import com.microservice.sede.dto.SedeDTO;
import com.microservice.sede.service.SedeService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping(value = "/api/v2/sede", produces = MediaTypes.HAL_JSON_VALUE)
public class SedeControllerV2 {

    private final SedeService service;
    private final SedeDTOAssembler assembler;

    public SedeControllerV2(SedeService service, SedeDTOAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<SedeDTO>> listar() {
        List<SedeDTO> list = service.listar().stream()
                .map(sede -> service.getSedeWithClassroom(sede.getId()))
                .collect(Collectors.toList());

        List<EntityModel<SedeDTO>> resources = list.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(resources,
                linkTo(methodOn(SedeControllerV2.class).listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<SedeDTO>> getSedeWithClassroom(@PathVariable Long id) {
        SedeDTO dto = service.getSedeWithClassroom(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(assembler.toModel(dto));
    }

    // Método para soporte del assembler (debe existir para los links)
    public ResponseEntity<SedeDTO> detalle(Long id) {
        SedeDTO dto = service.getSedeWithClassroom(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }
}
