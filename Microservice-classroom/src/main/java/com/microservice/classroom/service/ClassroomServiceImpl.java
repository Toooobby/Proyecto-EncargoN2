package com.microservice.classroom.service;

import com.microservice.classroom.model.Classroom;
import com.microservice.classroom.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomRepository repository;

    @Override
    public List<Classroom> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Classroom> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Classroom save(Classroom classroom) {
        return repository.save(classroom);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
