package com.microservice.sede.assembler;

import com.microservice.sede.controller.SedeControllerV2;
import com.microservice.sede.dto.SedeDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SedeDTOAssembler implements RepresentationModelAssembler<SedeDTO, EntityModel<SedeDTO>> {

    @Override
    public EntityModel<SedeDTO> toModel(SedeDTO dto) {
        EntityModel<SedeDTO> sedeModel = EntityModel.of(dto,
            linkTo(methodOn(SedeControllerV2.class).getSedeWithClassroom(dto.getId())).withSelfRel(),
            linkTo(methodOn(SedeControllerV2.class).listar()).withRel("sedes")
        );

        if (dto.getClassroom() != null && dto.getClassroom().getId() != null) {
            // Asumiendo que el microservicio classroom corre en localhost:8093 (ajustar si necesario)
            sedeModel.add(
                Link.of("http://localhost:8093/api/v2/classroom/" + dto.getClassroom().getId())
                    .withRel("classroom")
            );
        }

        return sedeModel;
    }
}
