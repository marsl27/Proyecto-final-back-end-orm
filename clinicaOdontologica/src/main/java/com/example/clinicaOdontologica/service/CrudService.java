package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.exceptions.ServiceBadRequestException;
import com.example.clinicaOdontologica.exceptions.ServiceNotFoundException;

import java.util.List;

public interface CrudService <T>{
    T guardar(T impl) throws ServiceNotFoundException, ServiceBadRequestException;
    T buscar(Integer id) throws ServiceNotFoundException, ServiceBadRequestException;
    List<T> buscarTodos() throws ServiceNotFoundException, ServiceBadRequestException;
    T actualizar(T impl) throws ServiceNotFoundException, ServiceBadRequestException;
    void eliminar(Integer id) throws ServiceNotFoundException, ServiceBadRequestException;
}
