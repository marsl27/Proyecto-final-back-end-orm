package com.example.clinicaOdontologica.service.impl;

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

    public PacienteDTO guardar(PacienteDTO pac){
        log.debug("Iniciando método guardar paciente");
        PacienteDTO pacienteGuardado = null;

        pac.setFechaIngreso(LocalDate.now()); //seteamos la fecha actual que es el dia en que se ingreso al paciente
        Domicilio dom = domicilioService.guardar(pac.toEntity().getDomicilio());// usamos el metodo del domicilioService para saber si ya existe el domicilio en la base de datos.
        pac.getDomicilio().setId(dom.getId());//Seteamos el id que nos retornó la linea anterior antes de guardar el paciente
        pacienteGuardado = new PacienteDTO(pacienteRepository.save(pac.toEntity()));

        log.debug("Terminó la ejecución del método guardar paciente");
        return pacienteGuardado;
    }

    @Override
    public PacienteDTO buscar(Integer id) {
        log.debug("Iniciando método buscar paciente");
        PacienteDTO pacienteBuscado = null;
        try{
            pacienteBuscado = new PacienteDTO(pacienteRepository.getById(id));
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

        for (Paciente pac:pacienteRepository.findAll()) {
            pacientes.add(new PacienteDTO(pac));
        }

        log.debug("Terminó la búsqueda de todos los pacientes");
        return pacientes;
    }

    @Override
    public PacienteDTO actualizar(PacienteDTO paciente) {
        log.debug("Actualizando paciente");
        PacienteDTO pacienteActualizado = null;

        Domicilio dom= domicilioService.guardar(paciente.getDomicilio().toEntity());
        paciente.getDomicilio().setId(dom.getId());
        pacienteActualizado = new PacienteDTO(pacienteRepository.save(paciente.toEntity()));


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

        List<Paciente> pacientes = pacienteRepository.findAll();
        Paciente paciente =pacienteRepository.getById(id);//buscamos el paciente a eliminar por id.

        Integer cont=0;

        for (Paciente pac:pacientes) {
            if(pac.getDomicilio().getId()==paciente.getDomicilio().getId()){ //comparamos si el id del domicilio del paciente que se quiere borrar
                //es igual a algún otro domicilio guardado para saber si está siendo usado por otro paciente. Si es asi, incrementamos el contador
                cont++;
            }
        }
        if(cont==1){ //Si el contador es igual a 1 es porque el domicilio solo esta siendo usado por el paciente que se quiere borrar. Por lo tanto, podemos borrarlo sin afectar
            //ningún paciente
            pacienteRepository.deleteById(id);
            domicilioService.eliminar(paciente.getDomicilio().getId());
        }else{
            pacienteRepository.deleteById(id);
        }

        log.debug("Finalizó la ejecución del método eliminar paciente");
    }
}
