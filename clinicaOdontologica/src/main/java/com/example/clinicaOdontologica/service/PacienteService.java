package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.model.PacienteDTO;
import org.springframework.stereotype.Service;

@Service
public interface PacienteService extends CrudService<PacienteDTO>{
    PacienteDTO buscarPorDni(String dni);

}
