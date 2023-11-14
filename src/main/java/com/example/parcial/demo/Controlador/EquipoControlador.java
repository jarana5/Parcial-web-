package com.example.parcial.demo.Controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parcial.demo.DTO.EquipoDTO;
import com.example.parcial.demo.Service.EquipoService;
@CrossOrigin(origins = "http://localhost:4800")
@RestController
@RequestMapping("/equipos")

public class EquipoControlador {

    @Autowired
    private EquipoService equipoService;
    @CrossOrigin
    @GetMapping
    public ResponseEntity<Iterable<EquipoDTO>> findAllEquipos() {
        Iterable<EquipoDTO> equipos = equipoService.findAll();
        return new ResponseEntity<>(equipos, HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<EquipoDTO> findEquipoById(@PathVariable Long id) {
        Optional<EquipoDTO> equipo = equipoService.findById(id);
        return equipo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<EquipoDTO> createEquipo(@RequestBody EquipoDTO equipoDTO) {
        EquipoDTO nuevoEquipo = equipoService.createEquipo(equipoDTO);
        return new ResponseEntity<>(nuevoEquipo, HttpStatus.CREATED);
    }
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<EquipoDTO> updateEquipo(@PathVariable Long id, @RequestBody EquipoDTO equipoDTO) {
        EquipoDTO equipoActualizado = equipoService.updateEquipo(id, equipoDTO);
        if (equipoActualizado != null) {
            return new ResponseEntity<>(equipoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipo(@PathVariable Long id) {
        equipoService.deleteEquipo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
