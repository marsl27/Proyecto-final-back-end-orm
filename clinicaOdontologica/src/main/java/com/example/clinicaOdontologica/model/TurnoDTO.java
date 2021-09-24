package com.example.clinicaOdontologica.model;

import com.example.clinicaOdontologica.persistence.entities.Paciente;
import com.example.clinicaOdontologica.persistence.entities.Turno;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TurnoDTO {

    private Integer id;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;
    private LocalDateTime fecha;
    private String anotaciones;


    public TurnoDTO(){

    }

    public TurnoDTO(Turno t){
        id = t.getId();
        paciente = new PacienteDTO(t.getPaciente());
        odontologo = new OdontologoDTO(t.getOdontologo());
        fecha = t.getFecha();
        anotaciones = t.getAnotaciones();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PacienteDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
    }

    public OdontologoDTO getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(OdontologoDTO odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getAnotaciones() {
        return anotaciones;
    }

    public void setAnotaciones(String anotaciones) {
        this.anotaciones = anotaciones;
    }

    public Turno toEntity(){
        Turno entity = new Turno();

        entity.setId(id);
        entity.setPaciente(paciente.toEntity());
        entity.setOdontologo(odontologo.toEntity());
        entity.setFecha(fecha);
        entity.setAnotaciones(anotaciones);

        return entity;
    }
}
