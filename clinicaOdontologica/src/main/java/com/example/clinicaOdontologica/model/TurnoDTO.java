package com.example.clinicaOdontologica.model;

import com.example.clinicaOdontologica.persistence.entities.Paciente;
import com.example.clinicaOdontologica.persistence.entities.Turno;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TurnoDTO {

    private Integer id;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;
    private LocalDate fecha;
    private LocalTime hora;
    private String anotaciones;


    public TurnoDTO(){

    }

    public TurnoDTO(Turno t){
        id = t.getId();
        paciente = new PacienteDTO(t.getPaciente());
        odontologo = new OdontologoDTO(t.getOdontologo());
        fecha = t.getFecha();
        hora= t.getHora();
        anotaciones = t.getAnotaciones();
    }

    public TurnoDTO(Integer idPaciente, Integer idOdontologo, LocalDate fecha, LocalTime hora){
        paciente = new PacienteDTO(1);
        odontologo = new OdontologoDTO(1);
        this.fecha = fecha;
        this.hora = hora;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
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
        entity.setHora(hora);
        entity.setAnotaciones(anotaciones);

        return entity;
    }
}
