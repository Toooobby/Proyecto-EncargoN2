package com.microservice.student.service;

import com.microservice.student.model.Student;
import com.microservice.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Student> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Student> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Student guardar(Student student) {
        return repository.save(student);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Student> buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Override
    public List<Student> buscarPorCurso(String curso) {
        return repository.findByCurso(curso);
    }

    @Override
    public List<Student> buscarPorNombreYCurso(String nombre, String curso) {
        return repository.findByNombreAndCurso(nombre, curso);
    }
}
