package com.microservice.student;

import com.microservice.student.model.Student;
import com.microservice.student.repository.StudentRepository;

import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTests {

    @Autowired
    private StudentRepository repository;

    private final Faker faker = new Faker();

    @Test
    @DisplayName("Guardar y recuperar estudiante")
    void saveAndFindStudent() {
        Student student = Student.builder()
                .nombre(faker.name().firstName())
                .apellido(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .courseId(1L)
                .build();

        repository.save(student);

        List<Student> students = repository.findByNombre(student.getNombre());
        assertThat(students).isNotEmpty();
        assertThat(students.get(0).getEmail()).isEqualTo(student.getEmail());
    }
}
