package com.example.parcial.demo.DTO;

public class JugadorDTO {
    private String nombre;
    private String apellido;
    private long numero;
    private EquipoDTO equipo;
    public JugadorDTO(){

    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public long getNumero() {
        return numero;
    }
    public void setNumero(long numero) {
        this.numero = numero;
    }
    public EquipoDTO getEquipo() {
        return equipo;
    }
    public void setEquipo(EquipoDTO equipo) {
        this.equipo = equipo;
    }
}
