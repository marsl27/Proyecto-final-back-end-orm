package com.example.clinicaOdontologica.service.impl;

import ch.qos.logback.classic.BasicConfigurator;
import com.example.clinicaOdontologica.model.PacienteDTO;
import com.example.clinicaOdontologica.persistence.entities.Domicilio;
import com.example.clinicaOdontologica.persistence.entities.Paciente;
import com.example.clinicaOdontologica.persistence.repositories.PacienteRepository;
import com.example.clinicaOdontologica.service.DomicilioService;
import com.example.clinicaOdontologica.service.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    DomicilioService domicilioService;

    final static Logger log = Logger.getLogger(PacienteServiceImpl.class);

    public PacienteDTO guardar(PacienteDTO pac){//UNIR EL METODO CON BUSCARPORDNI PARA QUE PRIMERO BUSQUE EL PACIENTE Y SI NO ESTA, LO AGREGUE
        log.debug("Iniciando método guardar paciente");
        PacienteDTO pacienteGuardado = null;
        try{
            pac.setFechaIngreso(LocalDate.now());
            Domicilio dom = domicilioService.guardar(pac.toEntity().getDomicilio());
            pac.getDomicilio().setId(dom.getId());
            pacienteGuardado = new PacienteDTO(pacienteRepository.save(pac.toEntity()));
        }catch(Exception e){
            log.error(e.getMessage());
        }

        log.debug("Terminó la ejecución del método guardar paciente");
        return pacienteGuardado;
    }

    @Override
    public PacienteDTO buscar(Integer id) {
        log.debug("Iniciando método buscar paciente");
        PacienteDTO pacienteBuscado = null;
        try{
            pacienteBuscado = new PacienteDTO(pacienteRepository.findById(id).get());
        }catch (Exception e){
            log.error(e.getMessage());
        }
        log.debug("Terminó la ejecución del método buscar paciente");
        return pacienteBuscado;
    }

    @Override
    public List<PacienteDTO> buscarTodos() {
        log.debug("Buscando todos los pacientes");
        List<PacienteDTO> pacientes = new ArrayList<>();
        try{
            for (Paciente pac:pacienteRepository.findAll()) {
                pacientes.add(new PacienteDTO(pac));
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        log.debug("Terminó la búsqueda de todos los pacientes");
        return pacientes;
    }

    @Override
    public PacienteDTO actualizar(PacienteDTO paciente) {
        log.debug("Actualizando paciente");
        PacienteDTO pacienteActualizado = null;

        try{
            Domicilio dom= domicilioService.guardar(paciente.getDomicilio().toEntity());
            paciente.getDomicilio().setId(dom.getId());
            pacienteActualizado = new PacienteDTO(pacienteRepository.save(paciente.toEntity()));
        }catch(Exception e){
            log.error(e.getMessage());
        }

        log.debug("Finalizó la ejecución del método actualizar paciente");
        return pacienteActualizado;
    }

    public PacienteDTO buscarPorDni(String dni){
        log.debug("Buscando paciente por dni");
        PacienteDTO pacienteBuscado = null;

        try{
            pacienteBuscado = new PacienteDTO(pacienteRepository.buscarPorDni(dni));
        }catch(Exception e){
            log.error(e.getMessage());
        }

        log.debug("Terminó la ejecución del método buscar paciente por dni");
        return pacienteBuscado;
    }

    @Override
    public void eliminar(Integer id) {
        log.debug("Eliminando paciente");

        try{
            List<Paciente> pacientes = pacienteRepository.findAll();
            Paciente paciente =pacienteRepository.getById(id);
            Integer cont=0;
            for (Paciente pac:pacientes) {
                if(pac.getDomicilio().getId()==paciente.getDomicilio().getId()){ //comparamos si los id de los domicilios son iguales para saber si tienen el mismo
                    cont++;
                }
            }
            if(cont==1){ //Si el contador es igual a 1 es porque el domicilio no se repetia
                pacienteRepository.deleteById(id);
                domicilioService.eliminar(paciente.getDomicilio().getId());
            }else{
                pacienteRepository.deleteById(id);
            }
        }catch (Exception e){
            e.getMessage();
        }
        log.debug("Finalizó la ejecución del método eliminar paciente");
    }
}
