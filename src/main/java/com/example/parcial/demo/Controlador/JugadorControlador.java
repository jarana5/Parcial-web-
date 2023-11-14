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

import com.example.parcial.demo.DTO.JugadorDTO;
import com.example.parcial.demo.Service.JugadorService;
@CrossOrigin(origins = "http://localhost:4800")
@RestController
@RequestMapping("/jugadores")

public class JugadorControlador {
    @Autowired
    private JugadorService jugadorService;
    @CrossOrigin
    @GetMapping
    public ResponseEntity<Iterable<JugadorDTO>> findAllJugadores() {
        Iterable<JugadorDTO> jugadores = jugadorService.findAll();
        return new ResponseEntity<>(jugadores, HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<JugadorDTO> findJugadorById(@PathVariable Long id) {
        Optional<JugadorDTO> jugador = jugadorService.findById(id);
        return jugador.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<JugadorDTO> createJugador(@RequestBody JugadorDTO jugadorDTO) {
        JugadorDTO nuevoJugador = jugadorService.createJugador(jugadorDTO);
        return new ResponseEntity<>(nuevoJugador, HttpStatus.CREATED);
    }
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<JugadorDTO> updateJugador(@PathVariable Long id, @RequestBody JugadorDTO jugadorDTO) {
        JugadorDTO jugadorActualizado = jugadorService.updateJugador(id, jugadorDTO);
        if (jugadorActualizado != null) {
            return new ResponseEntity<>(jugadorActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJugador(@PathVariable Long id) {
        jugadorService.deleteJugador(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
