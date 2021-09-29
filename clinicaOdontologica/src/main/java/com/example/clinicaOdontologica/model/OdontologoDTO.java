package com.example.clinicaOdontologica.model;


import com.example.clinicaOdontologica.persistence.entities.Odontologo;

public class OdontologoDTO {

    private Integer id;
    private String nombre;
    private String apellido;
    private Integer matricula;

    public OdontologoDTO(){

    }

    public OdontologoDTO(Integer id) {
        this.id = id;
    }

    public OdontologoDTO(Odontologo o){
        id = o.getId();
        nombre = o.getNombre();
        apellido = o.getApellido();
        matricula = o.getMatricula();
    }

    public OdontologoDTO(String nombre, String apellido, Integer matricula){
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
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

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Odontologo toEntity(){
        Odontologo entity = new Odontologo();

        entity.setId(id);
        entity.setNombre(nombre);
        entity.setApellido(apellido);
        entity.setMatricula(matricula);

        return entity;
    }
}
