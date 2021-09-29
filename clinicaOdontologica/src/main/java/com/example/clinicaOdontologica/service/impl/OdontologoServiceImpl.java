package com.example.clinicaOdontologica.service.impl;

import com.example.clinicaOdontologica.exceptions.ServiceBadRequestException;
import com.example.clinicaOdontologica.exceptions.ServiceNotFoundException;
import com.example.clinicaOdontologica.model.OdontologoDTO;
import com.example.clinicaOdontologica.persistence.entities.Odontologo;
import com.example.clinicaOdontologica.persistence.repositories.OdontologoRepository;
import com.example.clinicaOdontologica.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdontologoServiceImpl implements OdontologoService {

    @Autowired
    OdontologoRepository repository;

    final static Logger log = org.apache.log4j.Logger.getLogger(PacienteServiceImpl.class);


    @Override
    public OdontologoDTO guardar(OdontologoDTO odon) {
        log.debug("Iniciando método guardar odontólogo");
        OdontologoDTO odontologoGuardado = null;

        odontologoGuardado = new OdontologoDTO(repository.save(odon.toEntity()));

        log.debug("Terminó la ejecución del método guardar odontólogo");
        return odontologoGuardado;
    }

    @Override
    public OdontologoDTO buscar(Integer id) throws ServiceBadRequestException, ServiceNotFoundException{
        log.debug("Iniciando método buscar odontólogo");

        if(id == null || id<=0){
            throw new ServiceBadRequestException("El id del odontólogo debe ser un numero entero mayor a cero");
        }

        Odontologo odontologoBuscado = repository.findById(id).orElse(null);

        if(odontologoBuscado == null){
            throw new ServiceNotFoundException("No se encontró el odontólogo con id "+id);
        }

        OdontologoDTO odontologoDto = new OdontologoDTO(odontologoBuscado);
        log.debug("Terminó la ejecución del método buscar odontólogo");
        return odontologoDto;
    }

    @Override
    public List<OdontologoDTO> buscarTodos() throws ServiceNotFoundException{
        log.debug("Buscando todos los odontólogos");
        List<OdontologoDTO>odontologos = new ArrayList<>();

        for (Odontologo odon:repository.findAll()) {
            odontologos.add(new OdontologoDTO(odon));
        }

        if(odontologos.size()==0){
            throw new ServiceNotFoundException("No existen odontólogos.");
        }

        log.debug("Terminó la búsqueda de todos los odontólogos");
        return odontologos;
    }

    @Override
    public OdontologoDTO actualizar(OdontologoDTO odon) throws ServiceNotFoundException, ServiceBadRequestException {
        log.debug("Actualizando odontólogo");
        OdontologoDTO odontologoBuscado = buscar(odon.getId());
        OdontologoDTO odontologoActualizado = new OdontologoDTO(repository.save(odon.toEntity()));

        log.debug("Finalizó la ejecución del método actualizar odontólogo");
        return odontologoActualizado;
    }

    @Override
    public void eliminar(Integer id) throws ServiceNotFoundException, ServiceBadRequestException {
        log.debug("Eliminando odontólogo");

        if(id <= 0){
            throw new ServiceBadRequestException("El id del odontólogo debe ser un numero entero mayor a 0");
        }
        if(buscar(id) ==null){
            throw new ServiceNotFoundException("No existe el odontólogo con id "+ id);
        }

        repository.deleteById(id);

        log.debug("Finalizó la ejecución del método eliminar odontólogo");
    }
}
