package com.example.clinicaOdontologica.service.impl;

import com.example.clinicaOdontologica.model.OdontologoDTO;
import com.example.clinicaOdontologica.model.PacienteDTO;
import com.example.clinicaOdontologica.model.TurnoDTO;
import com.example.clinicaOdontologica.persistence.entities.Turno;
import com.example.clinicaOdontologica.persistence.repositories.TurnoRepository;
import com.example.clinicaOdontologica.service.OdontologoService;
import com.example.clinicaOdontologica.service.PacienteService;
import com.example.clinicaOdontologica.service.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TurnoServiceImpl implements TurnoService {

    @Autowired
    TurnoRepository turnoRepository;

    @Autowired
    PacienteService pacienteService;

    @Autowired
    OdontologoService odontologoService;

    final static Logger log = Logger.getLogger(PacienteServiceImpl.class);

    @Override
    public TurnoDTO guardar(TurnoDTO turno) {
        log.debug("Iniciando método guardar turno");
        TurnoDTO turnoGuardado = null;
        PacienteDTO paciente = pacienteService.buscar(turno.getPaciente().getId());
        OdontologoDTO odontologo = odontologoService.buscar(turno.getOdontologo().getId());

        if(paciente != null && odontologo != null){
            turno.setPaciente(paciente);
            turno.setOdontologo(odontologo);
            turnoGuardado = new TurnoDTO(turnoRepository.save(turno.toEntity()));
        }

        log.debug("Terminó la ejecución del método guardar turno");
        return turnoGuardado;
    }

    @Override
    public TurnoDTO buscar(Integer id) {
        log.debug("Iniciando método buscar turno");
        TurnoDTO turnoBuscado= new TurnoDTO(turnoRepository.getById(id));
        turnoBuscado.setPaciente(pacienteService.buscar(turnoBuscado.getPaciente().getId()));
        turnoBuscado.setOdontologo(odontologoService.buscar(turnoBuscado.getOdontologo().getId()));
        log.debug("Terminó la ejecución del método buscar turno");
        return turnoBuscado;
    }

    @Override
    public List<TurnoDTO> buscarTodos() {
        log.debug("Iniciando método buscar todos los turnos");
        List<TurnoDTO> turnos = new ArrayList<>();

        for (Turno turno:turnoRepository.findAll()) {
            TurnoDTO turnoBuscado = new TurnoDTO(turno);
            turnoBuscado.setPaciente(pacienteService.buscar(turnoBuscado.getPaciente().getId()));
            turnoBuscado.setOdontologo(odontologoService.buscar(turnoBuscado.getOdontologo().getId()));
            turnos.add(turnoBuscado);
        }

        log.debug("Terminó la búsqueda de todos los turnos");
        return turnos;
    }

    @Override
    public TurnoDTO actualizar(TurnoDTO turno) {
        log.debug("Iniciando método actualizar turno");
        Turno turnoBaseDatos = turnoRepository.getById(turno.getId());
        PacienteDTO pacienteBaseDatos = pacienteService.buscar(turno.getPaciente().getId());
        OdontologoDTO odontologoBaseDatos = odontologoService.buscar(turno.getOdontologo().getId());
        TurnoDTO turnoActualizado= null;

        if(turnoBaseDatos != null && pacienteBaseDatos != null && odontologoBaseDatos != null){
            turno.setPaciente(pacienteBaseDatos);
            turno.setOdontologo(odontologoBaseDatos);
            turnoActualizado = new TurnoDTO(turnoRepository.save(turno.toEntity()));
        }

        log.debug("Terminó la ejecución del método actualizar turno");
        return turnoActualizado;
    }

    @Override
    public void eliminar(Integer id) {
        log.debug("Iniciando método eliminar turno");
        turnoRepository.deleteById(id);
        log.debug("Finalizó la ejecución dle método eliminar turno");
    }
}
