package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.exceptions.ServiceBadRequestException;
import com.example.clinicaOdontologica.exceptions.ServiceNotFoundException;
import com.example.clinicaOdontologica.model.TurnoDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface TurnoService extends CrudService<TurnoDTO> {

    List<TurnoDTO> buscarTurnosHastaUnaFecha(LocalDate fecha) throws ServiceNotFoundException, ServiceBadRequestException;
}
