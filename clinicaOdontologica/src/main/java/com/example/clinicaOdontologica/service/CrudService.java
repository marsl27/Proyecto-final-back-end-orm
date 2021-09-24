package com.example.clinicaOdontologica.service;

import java.util.List;

public interface CrudService <T>{
    T guardar(T impl);
    T buscar(Integer id);
    List<T> buscarTodos();
    T actualizar(T impl);
    void eliminar(Integer id);
}
