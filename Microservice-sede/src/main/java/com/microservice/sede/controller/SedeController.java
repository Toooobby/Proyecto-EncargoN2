package com.microservice.sede.controller;

import com.microservice.sede.dto.SedeDTO;
import com.microservice.sede.model.Sede;
import com.microservice.sede.service.SedeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sede")
public class SedeController {

    private final SedeService service;

    public SedeController(SedeService service) {
        this.service = service;
    }

    // Listar todas las sedes
    @GetMapping
    public List<Sede> listar() {
        return service.listar();
    }

    // Obtener detalle por ID
    @GetMapping("/{id}")
    public ResponseEntity<Sede> detalle(@PathVariable Long id) {
        return service.porId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Obtener sede junto con información del aula (Classroom)
    @GetMapping("/with-classroom/{id}")
    public ResponseEntity<SedeDTO> getSedeWithClassroom(@PathVariable("id") Long id) {
        SedeDTO sedeDTO = service.getSedeWithClassroom(id);
        if (sedeDTO != null) {
            return ResponseEntity.ok(sedeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva sede
    @PostMapping
    public Sede crear(@RequestBody Sede sede) {
        return service.guardar(sede);
    }

    // Eliminar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por nombre
    @GetMapping("/buscar/nombre/{nombre}")
    public List<Sede> buscarPorNombre(@PathVariable String nombre) {
        return service.buscarPorNombre(nombre);
    }


}
