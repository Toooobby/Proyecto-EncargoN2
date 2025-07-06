package com.microservice.student.assembler;

import com.microservice.student.controller.StudentControllerV2;
import com.microservice.student.dto.StudentDTO;
import com.microservice.student.model.Student;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class StudentModelAssembler implements RepresentationModelAssembler<Student, EntityModel<Student>> {

    @Override
    @NonNull
    public EntityModel<Student> toModel(@NonNull Student student) {
        return EntityModel.of(student,
            linkTo(methodOn(StudentControllerV2.class).detalle(student.getId())).withSelfRel(),
            linkTo(methodOn(StudentControllerV2.class).listar()).withRel("students")
        );
    }

    public Object toModel(StudentDTO studentWithCourse) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toModel'");
    }
}
