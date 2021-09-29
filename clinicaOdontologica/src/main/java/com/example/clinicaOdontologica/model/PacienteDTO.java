package com.example.clinicaOdontologica.model;

import com.example.clinicaOdontologica.persistence.entities.Paciente;

import java.time.LocalDate;

public class PacienteDTO{

    private Integer id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private LocalDate fechaIngreso;
    private DomicilioDTO domicilio;

    public PacienteDTO(){

    }

    public PacienteDTO(Integer id) {
        this.id = id;
    }

    public PacienteDTO(Paciente p){
        id = p.getId();
        nombre = p.getNombre();
        apellido = p.getApellido();
        dni = p.getDni();
        fechaIngreso = p.getFechaIngreso();
        domicilio = new DomicilioDTO(p.getDomicilio());
    }

    public PacienteDTO(String nombre, String apellido, Integer dni, DomicilioDTO domicilio){
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public DomicilioDTO getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioDTO domicilio) {
        this.domicilio = domicilio;
    }

    public Paciente toEntity(){
        Paciente entity = new Paciente();

        entity.setId(id);
        entity.setApellido(apellido);
        entity.setDni(dni);
        entity.setNombre(nombre);
        entity.setFechaIngreso(fechaIngreso);
        entity.setDomicilio(domicilio.toEntity());

        return entity;
    }
}
