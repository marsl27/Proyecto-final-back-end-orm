package com.example.clinicaOdontologica.service.impl;

import com.example.clinicaOdontologica.exceptions.ServiceBadRequestException;
import com.example.clinicaOdontologica.exceptions.ServiceNotFoundException;
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
    public TurnoDTO guardar(TurnoDTO turno) throws ServiceNotFoundException, ServiceBadRequestException {
        log.debug("Iniciando método guardar turno");
        TurnoDTO turnoGuardado = null;
        PacienteDTO paciente = pacienteService.buscar(turno.getPaciente().getId());
        OdontologoDTO odontologo = odontologoService.buscar(turno.getOdontologo().getId());

        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turnoGuardado = new TurnoDTO(turnoRepository.save(turno.toEntity()));

        log.debug("Terminó la ejecución del método guardar turno");
        return turnoGuardado;
    }

    @Override
    public TurnoDTO buscar(Integer id) throws ServiceNotFoundException, ServiceBadRequestException {
        log.debug("Iniciando método buscar turno");

        if(id == null || id<=0){
            throw new ServiceBadRequestException("El id del turno debe ser un numero entero mayor a cero");
        }

        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);

        if(turnoBuscado == null){
            throw new ServiceNotFoundException("No se encontró el turno con id "+id);
        }

        TurnoDTO turnoDto = new TurnoDTO(turnoBuscado);
        turnoDto.setPaciente(pacienteService.buscar(turnoBuscado.getPaciente().getId()));
        turnoDto.setOdontologo(odontologoService.buscar(turnoBuscado.getOdontologo().getId()));

        log.debug("Terminó la ejecución del método buscar turno");
        return turnoDto;
    }

    @Override
    public List<TurnoDTO> buscarTodos() throws ServiceNotFoundException, ServiceBadRequestException {
        log.debug("Iniciando método buscar todos los turnos");
        List<TurnoDTO> turnos = new ArrayList<>();

        for (Turno turno:turnoRepository.findAll()) {
            TurnoDTO turnoBuscado = new TurnoDTO(turno);

            turnoBuscado.setPaciente(pacienteService.buscar(turnoBuscado.getPaciente().getId()));
            turnoBuscado.setOdontologo(odontologoService.buscar(turnoBuscado.getOdontologo().getId()));
            turnos.add(turnoBuscado);
        }

        if(turnos.size()==0){
            throw new ServiceNotFoundException("No existen turnos registrados");
        }

        log.debug("Terminó la búsqueda de todos los turnos");
        return turnos;
    }

    @Override
    public TurnoDTO actualizar(TurnoDTO turno) throws ServiceNotFoundException, ServiceBadRequestException{
        log.debug("Iniciando método actualizar turno");

        if(turno.getId()==null || turno.getId()<=0){
            throw new ServiceBadRequestException("El id del turno debe ser un numero entero mayor a cero");
        }
        if(buscar(turno.getId()) == null){
            throw new ServiceNotFoundException("No se encontró el turno con id "+turno.getId());
        }

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
    public void eliminar(Integer id) throws ServiceNotFoundException, ServiceBadRequestException {
        log.debug("Iniciando método eliminar turno");

        if(id<=0){
            throw new ServiceBadRequestException("El id del turno debe ser un numero entero mayor a cero");
        }

        if(buscar(id)==null){
            throw new ServiceNotFoundException("No existe el turno con id "+id);
        }

        turnoRepository.deleteById(id);
        log.debug("Finalizó la ejecución dle método eliminar turno");
    }
}
