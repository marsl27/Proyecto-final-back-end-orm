package com.example.clinicaOdontologica.service.impl;

import com.example.clinicaOdontologica.model.OdontologoDTO;
import com.example.clinicaOdontologica.model.PacienteDTO;
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

        try{
            odontologoGuardado = new OdontologoDTO(repository.save(odon.toEntity()));
        }catch (Exception e){
            log.error(e.getMessage());
        }

        log.debug("Terminó la ejecución del método guardar odontólogo");
        return odontologoGuardado;
    }

    @Override
    public OdontologoDTO buscar(Integer id) {
        log.debug("Iniciando método buscar odontólogo");
        OdontologoDTO odontologoBuscado = null;

        try{
            odontologoBuscado = new OdontologoDTO(repository.findById(id).get());
        }catch (Exception e){
            log.error(e.getMessage());
        }

        log.debug("Terminó la ejecución del método buscar odontólogo");
        return odontologoBuscado;
    }

    @Override
    public List<OdontologoDTO> buscarTodos() {
        log.debug("Buscando todos los odontólogos");
        List<OdontologoDTO>odontologos = new ArrayList<>();

        try{
            for (Odontologo odon:repository.findAll()) {
                odontologos.add(new OdontologoDTO(odon));
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }

        log.debug("Terminó la búsqueda de todos los odontólogos");
        return odontologos;
    }

    @Override
    public OdontologoDTO actualizar(OdontologoDTO odon) {
        log.debug("Actualizando odontólogo");
        OdontologoDTO odontologoActualizado = null;

        try{
            odontologoActualizado = new OdontologoDTO(repository.save(odon.toEntity()));
        }catch(Exception e){
            log.error(e.getMessage());
        }

        log.debug("Finalizó la ejecución del método actualizar odontólogo");
        return odontologoActualizado;
    }

    @Override
    public void eliminar(Integer id) {
        log.debug("Eliminando odontólogo");
        try{
            repository.deleteById(id);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        log.debug("Finalizó la ejecución del método eliminar odontólogo");
    }
}
