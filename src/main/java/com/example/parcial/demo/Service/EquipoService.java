package com.example.parcial.demo.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parcial.demo.DTO.EquipoDTO;
import com.example.parcial.demo.Modelo.Equipo;
import com.example.parcial.demo.Repository.EquipoRepository;
@Service
public class EquipoService {
    @Autowired
    private EquipoRepository equipoRepository;

    public Iterable<EquipoDTO> findAll() {
    Iterable<Equipo> equipos = equipoRepository.findAll();
    return StreamSupport.stream(equipos.spliterator(), false)
            .map(this::convertirDto)
            .collect(Collectors.toList());
}
public Optional<EquipoDTO> findById(Long id) {
    Optional<Equipo> equipo = equipoRepository.findById(id);

    return equipo.map(this::convertirDto);
}

public EquipoDTO createEquipo(EquipoDTO equipoDTO) {
    Equipo Equipo = convertirADominio(equipoDTO);
    Equipo nuevoEquipo = equipoRepository.save(Equipo);
    return convertirDto(nuevoEquipo);
}

public EquipoDTO updateEquipo(Long id, EquipoDTO EquipoDTO) {
    Equipo EquipoExistente = equipoRepository.findById(id).orElse(null);

    if (EquipoExistente != null) {
        BeanUtils.copyProperties(convertirADominio(EquipoDTO), EquipoExistente, "id");
        Equipo EquipoActualizado = equipoRepository.save(EquipoExistente);
        return convertirDto(EquipoActualizado);
    }

    return null;
}

public void deleteEquipo(Long id) {
    equipoRepository.deleteById(id);
}
 private EquipoDTO convertirDto(Equipo equipo)  {
        EquipoDTO EquipoDTO = new EquipoDTO();
        BeanUtils.copyProperties(equipo, EquipoDTO);
        return EquipoDTO;
    }

    private Equipo convertirADominio(EquipoDTO EquipoDTO) {
        Equipo equipo = new Equipo();
        BeanUtils.copyProperties(EquipoDTO, equipo);
        return equipo;
    }
}
