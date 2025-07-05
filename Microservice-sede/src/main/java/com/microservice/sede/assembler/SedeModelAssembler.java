package com.microservice.sede.assembler;

import com.microservice.sede.controller.SedeControllerV2;
import com.microservice.sede.model.Sede;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SedeModelAssembler implements RepresentationModelAssembler<Sede, EntityModel<Sede>> {

    @Override
    @NonNull
    public EntityModel<Sede> toModel(@NonNull Sede sede) {
        return EntityModel.of(sede,
            linkTo(methodOn(SedeControllerV2.class).detalle(sede.getId())).withSelfRel(),
            linkTo(methodOn(SedeControllerV2.class).listar()).withRel("sedes")
        );
    }
}
