package com.example.clinicaOdontologica.service.impl;

import com.example.clinicaOdontologica.exceptions.ServiceBadRequestException;
import com.example.clinicaOdontologica.exceptions.ServiceNotFoundException;
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

    public PacienteDTO guardar(PacienteDTO pac) throws ServiceNotFoundException, ServiceBadRequestException {
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
    public PacienteDTO buscar(Integer id) throws ServiceNotFoundException, ServiceBadRequestException{
        log.debug("Iniciando método buscar paciente");

        if(id == null || id<=0){
            throw new ServiceBadRequestException("El id del paciente debe ser un numero entero mayor a cero");
        }

        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);

        if(pacienteBuscado == null){
            throw new ServiceNotFoundException("No se encontró el paciente con id "+id);
        }

        PacienteDTO pacienteDto = new PacienteDTO(pacienteBuscado);

        log.debug("Terminó la ejecución del método buscar paciente");
        return pacienteDto;
    }

    @Override
    public List<PacienteDTO> buscarTodos() throws ServiceNotFoundException{
        log.debug("Buscando todos los pacientes");
        List<PacienteDTO> pacientes = new ArrayList<>();

        for (Paciente pac:pacienteRepository.findAll()) {
            pacientes.add(new PacienteDTO(pac));
        }

        if(pacientes.size()==0){
            throw new ServiceNotFoundException("No existen pacientes.");
        }


        log.debug("Terminó la búsqueda de todos los pacientes");
        return pacientes;
    }

    @Override
    public PacienteDTO actualizar(PacienteDTO paciente) throws ServiceNotFoundException, ServiceBadRequestException {
        log.debug("Actualizando paciente");
        PacienteDTO pacienteBuscado= buscar(paciente.getId()); //Al buscar, utilizamos las excepciones del metodo buscar asi que si no existe el paciente,
        //va a arrojar dicha excepcion
        PacienteDTO pacienteActualizado = null;

        Domicilio dom= domicilioService.guardar(paciente.getDomicilio().toEntity());
        paciente.getDomicilio().setId(dom.getId());
        pacienteActualizado = new PacienteDTO(pacienteRepository.save(paciente.toEntity()));


        log.debug("Finalizó la ejecución del método actualizar paciente");
        return pacienteActualizado;
    }

    public PacienteDTO buscarPorDni(Integer dni) throws ServiceBadRequestException, ServiceNotFoundException{
        log.debug("Buscando paciente por dni");

        if(dni <= 1){
            throw new ServiceBadRequestException("El dni del paciente no puede ser negativo.");
        }

        Paciente pacienteBuscado = pacienteRepository.buscarPorDni(dni);

        if(pacienteBuscado == null){
            throw new ServiceNotFoundException("No se encontró el paciente con dni "+dni);
        }

        PacienteDTO pacienteDTO = new PacienteDTO(pacienteBuscado);
        log.debug("Terminó la ejecución del método buscar paciente por dni");
        return pacienteDTO;
    }

    @Override
    public void eliminar(Integer id) throws ServiceNotFoundException, ServiceBadRequestException {
        log.debug("Eliminando paciente");

        List<Paciente> pacientes = pacienteRepository.findAll();

        if(id<= 0){
            throw new ServiceBadRequestException("El id del paciente debe ser un numero entero mayor a cero");
        }

        PacienteDTO paciente = buscar(id);//buscamos el paciente a eliminar por id.

        if(paciente==null){
            throw new ServiceNotFoundException("No existe el paciente con id "+id);
        }

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
