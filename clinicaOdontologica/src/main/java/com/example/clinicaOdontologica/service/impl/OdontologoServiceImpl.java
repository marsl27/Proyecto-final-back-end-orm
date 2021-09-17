package com.example.clinicaOdontologica.service.impl;

import com.example.clinicaOdontologica.persistence.entities.Odontologo;
import com.example.clinicaOdontologica.persistence.repositories.OdontologoRepository;
import com.example.clinicaOdontologica.service.OdontologoService;
import com.example.clinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoServiceImpl implements OdontologoService {

    @Autowired
    OdontologoRepository repository;

    @Override
    public Odontologo guardar(Odontologo odon) {
        return repository.save(odon);
    }

    @Override
    public Odontologo buscar(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public Odontologo actualizar(Odontologo odon, Integer id) {
        odon.setId(id);
        return repository.save(odon);
    }

    @Override
    public void eliminar(Integer id) {

    }
}
