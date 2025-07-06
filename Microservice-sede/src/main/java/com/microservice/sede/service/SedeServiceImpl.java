package com.microservice.sede.service;

import com.microservice.sede.client.ClassroomClient;
import com.microservice.sede.dto.ClassroomDTO;
import com.microservice.sede.dto.SedeDTO;
import com.microservice.sede.model.Sede;
import com.microservice.sede.repository.SedeRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SedeServiceImpl implements SedeService {

    private final SedeRepository repository;
    private final ClassroomClient classroomClient;

    public SedeServiceImpl(SedeRepository repository, ClassroomClient classroomClient) {
        this.repository = repository;
        this.classroomClient = classroomClient;
    }

    @Override
    public List<Sede> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Sede> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Sede guardar(Sede sede) {
        return repository.save(sede);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Sede> buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Override
    public SedeDTO getSedeWithClassroom(Long id) {
        Optional<Sede> optionalSede = repository.findById(id);

        if (optionalSede.isPresent()) {
            Sede sede = optionalSede.get();

            ClassroomDTO classroom = null;
            try {
                if (sede.getClassroomId() != null) {
                    classroom = classroomClient.getClassroomById(sede.getClassroomId());
                }
            } catch (FeignException.NotFound e) {
                // Aula no encontrada: se deja null
                System.out.println("Classroom no encontrado para id = " + sede.getClassroomId());
            }

            return new SedeDTO(
                sede.getId(),
                sede.getNombre(),
                sede.getDireccion(),
                sede.getCiudad(),
                classroom
            );
        }

        return null;
    }
}
