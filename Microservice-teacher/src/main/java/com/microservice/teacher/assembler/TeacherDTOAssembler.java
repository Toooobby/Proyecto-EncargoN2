package com.microservice.teacher.assembler;

import com.microservice.teacher.controller.TeacherControllerV2;
import com.microservice.teacher.dto.TeacherDTO;
import com.microservice.teacher.dto.TeacherResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TeacherDTOAssembler implements RepresentationModelAssembler<TeacherDTO, EntityModel<TeacherResponseDTO>> {

    @Override
    public EntityModel<TeacherResponseDTO> toModel(TeacherDTO dto) {
        TeacherResponseDTO responseDTO = new TeacherResponseDTO(
            dto.getId(),
            dto.getNombre(),
            dto.getApellido(),
            dto.getEmail(),
            dto.getCourse() // asumiendo CourseDTO dentro de TeacherResponseDTO
        );

        EntityModel<TeacherResponseDTO> teacherModel = EntityModel.of(responseDTO,
            linkTo(methodOn(TeacherControllerV2.class).detalle(dto.getId())).withSelfRel(),
            linkTo(methodOn(TeacherControllerV2.class).listar()).withRel("teachers")
        );

        if (dto.getCourse() != null && dto.getCourse().getId() != null) {
            // Ajusta URL según dónde esté expuesto el microservicio de Course
            teacherModel.add(
                Link.of("http://localhost:8091/api/v2/course/" + dto.getCourse().getId())
                    .withRel("course")
            );
        }

        return teacherModel;
    }
}
