package com.microservice.classroom.service;

import com.microservice.classroom.client.CourseClient;
import com.microservice.classroom.dto.CourseDTO;
import com.microservice.classroom.dto.ClassroomDTO;
import com.microservice.classroom.model.Classroom;
import com.microservice.classroom.repository.ClassroomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository repository;
    private final CourseClient courseClient;

    public ClassroomServiceImpl(ClassroomRepository repository, CourseClient courseClient) {
        this.repository = repository;
        this.courseClient = courseClient;
    }

    @Override
    public List<Classroom> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Classroom> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Classroom guardar(Classroom classroom) {
        return repository.save(classroom);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Classroom> buscarPorNumero(String numero) {
        return repository.findByNumero(numero);
    }

    @Override
    public List<Classroom> buscarPorCourseId(Long courseId) {
        return repository.findByCourseId(courseId);
    }

    @Override
    public List<Classroom> buscarPorNumeroYCourseId(String numero, Long courseId) {
        return repository.findByNumeroAndCourseId(numero, courseId);
    }

    // Nuevo método para devolver estudiante con info de curso
    @Override
public ClassroomDTO getClassroomWithCourse(Long id) {
    Optional<Classroom> optionalClassroom = repository.findById(id);

    if (optionalClassroom.isPresent()) {
        Classroom classroom = optionalClassroom.get();

        CourseDTO course = null;
        if (classroom.getCourseId() != null) {
            course = courseClient.getCourseById(classroom.getCourseId());
        }

        return new ClassroomDTO(
                classroom.getId(),
                classroom.getNumero(),
                classroom.getUbicacion(),
                classroom.getCapacidad(),
                course // el quinto parámetro
        );
    }
    return null;
}

}
