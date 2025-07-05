package com.microservice.classroom.assembler;

import com.microservice.classroom.controller.ClassroomControllerV2;
import com.microservice.classroom.dto.ClassroomDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClassroomDTOAssembler implements RepresentationModelAssembler<ClassroomDTO, EntityModel<ClassroomDTO>> {

    @Override
    public EntityModel<ClassroomDTO> toModel(ClassroomDTO dto) {
        EntityModel<ClassroomDTO> classroomModel = EntityModel.of(dto,
            linkTo(methodOn(ClassroomControllerV2.class).getClassroomWithCourse(dto.getId())).withSelfRel(),
            linkTo(methodOn(ClassroomControllerV2.class).listar()).withRel("classrooms")
        );

        if (dto.getCourse() != null && dto.getCourse().getId() != null) {
            // Aquí asumes que el microservicio Course está en localhost:8091 (ajusta según tu entorno)
            classroomModel.add(
                Link.of("http://localhost:8091/api/v2/course/" + dto.getCourse().getId())
                    .withRel("course")
            );
        }

        return classroomModel;
    }
}
