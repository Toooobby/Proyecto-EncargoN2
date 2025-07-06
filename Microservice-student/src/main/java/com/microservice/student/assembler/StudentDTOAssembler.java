package com.microservice.student.assembler;

import com.microservice.student.controller.StudentControllerV2;
import com.microservice.student.dto.StudentDTO;
import com.microservice.student.dto.StudentResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class StudentDTOAssembler implements RepresentationModelAssembler<StudentDTO, EntityModel<StudentResponseDTO>> {

    @Override
    public EntityModel<StudentResponseDTO> toModel(StudentDTO dto) {
        // Crear y mapear StudentResponseDTO
        StudentResponseDTO responseDTO = new StudentResponseDTO();
        responseDTO.setId(dto.getId());
        responseDTO.setNombre(dto.getNombre());
        responseDTO.setApellido(dto.getApellido());
        responseDTO.setEmail(dto.getEmail());
        responseDTO.setCourse(dto.getCourse());

        // Envolver en EntityModel con enlaces HATEOAS
        EntityModel<StudentResponseDTO> studentModel = EntityModel.of(responseDTO,
                linkTo(methodOn(StudentControllerV2.class).detalle(dto.getId())).withSelfRel(),
                linkTo(methodOn(StudentControllerV2.class).listar()).withRel("students")
        );

        if (dto.getCourse() != null && dto.getCourse().getId() != null) {
            studentModel.add(
                    Link.of("http://localhost:8091/api/v2/course/" + dto.getCourse().getId())
                            .withRel("course")
            );
        }

        return studentModel;
    }
}