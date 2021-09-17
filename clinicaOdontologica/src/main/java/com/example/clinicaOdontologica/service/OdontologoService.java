package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.persistence.entities.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OdontologoService {
    Odontologo guardar(Odontologo odon);
    Odontologo buscar(Integer id);
    List<Odontologo> buscarTodos();
    Odontologo actualizar(Odontologo odon, Integer id);
    void eliminar(Integer id);

}
