package com.example.parcial.demo.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parcial.demo.DTO.JugadorDTO;
import com.example.parcial.demo.Modelo.Jugador;
import com.example.parcial.demo.Repository.JugadorRepository;

@Service
public class JugadorService {
    
    @Autowired
    private JugadorRepository jugadorRepository;
    public Iterable<JugadorDTO> findAll() {
    Iterable<Jugador> Jugadors = jugadorRepository.findAll();
    return StreamSupport.stream(Jugadors.spliterator(), false)
            .map(this::convertirDto)
            .collect(Collectors.toList());
}
public Optional<JugadorDTO> findById(Long id) {
    Optional<Jugador> jugador = jugadorRepository.findById(id);

    return jugador.map(this::convertirDto);
}

public JugadorDTO createJugador(JugadorDTO jugadorDTO) {
    Jugador jugador = convertirADominio(jugadorDTO);
    Jugador nuevoJugador = jugadorRepository.save(jugador);
    return convertirDto(nuevoJugador);
}

public JugadorDTO updateJugador(Long id, JugadorDTO jugadorDTO) {
    Jugador JugadorExistente = jugadorRepository.findById(id).orElse(null);

    if (JugadorExistente != null) {
        BeanUtils.copyProperties(convertirADominio(jugadorDTO), JugadorExistente, "id");
        Jugador JugadorActualizado = jugadorRepository.save(JugadorExistente);
        return convertirDto(JugadorActualizado);
    }

    return null;
}

public void deleteJugador(Long id) {
    jugadorRepository.deleteById(id);
}
 private JugadorDTO convertirDto(Jugador Jugador)  {
        JugadorDTO jugadorDTO = new JugadorDTO();
        BeanUtils.copyProperties(Jugador, jugadorDTO);
        return jugadorDTO;
    }

    private Jugador convertirADominio(JugadorDTO jugadorDTO) {
        Jugador jugador = new Jugador();
        BeanUtils.copyProperties(jugadorDTO, jugador);
        return jugador;
    }
}
