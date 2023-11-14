package com.example.parcial.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.parcial.demo.DTO.EquipoDTO;
import com.example.parcial.demo.Modelo.Equipo;
import com.example.parcial.demo.Modelo.Jugador;

public interface EquipoRepository extends CrudRepository<Equipo,Long>{
       List<Equipo> findallEquipos();
       List<Equipo> findbynameequipo();
}
