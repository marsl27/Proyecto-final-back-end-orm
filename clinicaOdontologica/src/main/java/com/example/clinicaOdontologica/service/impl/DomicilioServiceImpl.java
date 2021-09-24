package com.example.clinicaOdontologica.service.impl;

import com.example.clinicaOdontologica.persistence.entities.Domicilio;
import com.example.clinicaOdontologica.persistence.repositories.DomicilioRepository;

import com.example.clinicaOdontologica.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DomicilioServiceImpl implements DomicilioService {

    @Autowired
    DomicilioRepository repository;

    @Override
    public Domicilio guardar(Domicilio dom) { //hice un metodo que busque el domicilio por calle, numero, provincia y localidad, para no guardarlos repetidos.
        //si lo encuentra lo retorna, sino lo guarda y lo retorna
        Domicilio domicilioBuscado= repository.buscarDomicilioPorCalleNumeroLocalidadProvincia(dom.getCalle(), dom.getNumero(), dom.getLocalidad(), dom.getProvincia());
        if (domicilioBuscado == null){
            domicilioBuscado = repository.save(dom);
        }
        return domicilioBuscado;
    }

    @Override
    public Domicilio buscar(Integer id) {
        return null;
    }

    @Override
    public List<Domicilio> buscarTodos() {
        List<Domicilio> domicilios = new ArrayList<>();

        return repository.findAll();
    }

    @Override
    public Domicilio actualizar(Domicilio dom) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
