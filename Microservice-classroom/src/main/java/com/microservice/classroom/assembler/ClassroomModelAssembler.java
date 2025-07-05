package com.microservice.classroom.assembler;

import com.microservice.classroom.controller.ClassroomControllerV2;
import com.microservice.classroom.model.Classroom;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClassroomModelAssembler implements RepresentationModelAssembler<Classroom, EntityModel<Classroom>> {

    @Override
    @NonNull
    public EntityModel<Classroom> toModel(@NonNull Classroom classroom) {
        return EntityModel.of(classroom,
            linkTo(methodOn(ClassroomControllerV2.class).detalle(classroom.getId())).withSelfRel(),
            linkTo(methodOn(ClassroomControllerV2.class).listar()).withRel("classrooms")
        );
    }
}
