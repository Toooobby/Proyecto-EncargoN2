package com.microservice.teacher.service;

import com.microservice.teacher.model.Teacher;
import com.microservice.teacher.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository repository;

    public TeacherServiceImpl(TeacherRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Teacher> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Teacher> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Teacher guardar(Teacher teacher) {
        return repository.save(teacher);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
