package com.example.parcial.demo.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.parcial.demo.Modelo.Jugador;

public interface JugadorRepository extends CrudRepository<Jugador,Long>{
 List <Jugador> findbynameJugadors(String nombre) ; 
List <Jugador> findbyequipo(String nomequipo);
} 
