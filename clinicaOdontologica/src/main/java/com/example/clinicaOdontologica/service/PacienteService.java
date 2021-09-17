package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.persistence.entities.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PacienteService {
    Paciente guardar(Paciente pac);
    Paciente buscar(Integer id);
    List<Paciente> buscarTodos();
    Paciente actualizar(Paciente pac);
    void eliminar(Integer id);
    Paciente buscarPorDni(String dni);

}
