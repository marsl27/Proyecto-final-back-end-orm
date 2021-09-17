package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.persistence.entities.Domicilio;
import com.example.clinicaOdontologica.persistence.entities.Odontologo;

import java.util.List;

public interface DomicilioService {
    Domicilio buscar(Integer id);
    List< Domicilio> buscarTodos();
    Domicilio actualizar( Domicilio dom, Integer id);
    void eliminar(Integer id);
}
