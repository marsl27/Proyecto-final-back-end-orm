package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.persistence.entities.Odontologo;

import java.util.List;

public interface CrudService <T>{
    T guardar(T impl);
    T buscar(Integer id);
    List<T> buscarTodos();
    T actualizar(T impl);
    void eliminar(Integer id);
}
