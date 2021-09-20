package com.example.clinicaOdontologica.service.impl;

import com.example.clinicaOdontologica.model.OdontologoDTO;
import com.example.clinicaOdontologica.persistence.entities.Odontologo;
import com.example.clinicaOdontologica.persistence.repositories.OdontologoRepository;
import com.example.clinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdontologoServiceImpl implements OdontologoService {

    @Autowired
    OdontologoRepository repository;

    @Override
    public OdontologoDTO guardar(OdontologoDTO odon) {
        return new OdontologoDTO(repository.save(odon.toEntity()));
    }

    @Override
    public OdontologoDTO buscar(Integer id) {
        return new OdontologoDTO(repository.findById(id).get());
    }

    @Override
    public List<OdontologoDTO> buscarTodos() {
        List<OdontologoDTO>odontologos = new ArrayList<>();

        for (Odontologo odon:repository.findAll()) {
            odontologos.add(new OdontologoDTO(odon));
        }
        return odontologos;
    }

    @Override
    public OdontologoDTO actualizar(OdontologoDTO odon) {
        return new OdontologoDTO(repository.save(odon.toEntity()));
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
