package com.microservice.sede;

import net.datafaker.Faker;
import com.microservice.sede.model.Sede;
import com.microservice.sede.repository.SedeRepository;
import com.microservice.sede.service.SedeService;
import com.microservice.sede.service.SedeServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SedeServiceTests {

    private SedeRepository sedeRepository;
    private SedeService sedeService;
    private Faker faker;

    @BeforeEach
    public void setup() {
        sedeRepository = Mockito.mock(SedeRepository.class);
        sedeService = new SedeServiceImpl(sedeRepository, null);
        faker = new Faker();
    }

    @Test
    public void listar_deberiaRetornarListaDeSedes() {
        Sede sede = new Sede();
        sede.setId(faker.number().randomNumber());
        sede.setNombre(faker.company().name());
        sede.setDireccion(faker.address().fullAddress());

        when(sedeRepository.findAll()).thenReturn(List.of(sede));

        List<Sede> sedes = sedeService.listar();

        assertNotNull(sedes);
        assertFalse(sedes.isEmpty());
        assertEquals(sede.getNombre(), sedes.get(0).getNombre());

        verify(sedeRepository, times(1)).findAll();
    }

    @Test
    public void porId_deberiaRetornarSedeCuandoExiste() {
        Sede sede = new Sede();
        sede.setId(faker.number().randomNumber());
        sede.setNombre(faker.company().name());
        sede.setDireccion(faker.address().fullAddress());

        when(sedeRepository.findById(sede.getId())).thenReturn(Optional.of(sede));

        Optional<Sede> resultado = sedeService.porId(sede.getId());

        assertTrue(resultado.isPresent());
        assertEquals(sede.getNombre(), resultado.get().getNombre());

        verify(sedeRepository, times(1)).findById(sede.getId());
    }

    @Test
    public void porId_deberiaRetornarEmptyCuandoNoExiste() {
        long idNoExistente = faker.number().randomNumber();

        when(sedeRepository.findById(idNoExistente)).thenReturn(Optional.empty());

        Optional<Sede> resultado = sedeService.porId(idNoExistente);

        assertFalse(resultado.isPresent());

        verify(sedeRepository, times(1)).findById(idNoExistente);
    }
}
