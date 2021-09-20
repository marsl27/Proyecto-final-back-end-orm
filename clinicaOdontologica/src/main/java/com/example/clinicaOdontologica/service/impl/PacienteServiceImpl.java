package com.example.clinicaOdontologica.service.impl;

import com.example.clinicaOdontologica.model.PacienteDTO;
import com.example.clinicaOdontologica.persistence.entities.Domicilio;
import com.example.clinicaOdontologica.persistence.entities.Paciente;
import com.example.clinicaOdontologica.persistence.repositories.PacienteRepository;
import com.example.clinicaOdontologica.service.DomicilioService;
import com.example.clinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    DomicilioService domicilioService;

    public PacienteDTO guardar(PacienteDTO pac){//UNIR EL METODO CON BUSCARPORDNI PARA QUE PRIMERO BUSQUE EL PACIENTE Y SI NO ESTA, LO AGREGUE
        pac.setFechaIngreso(LocalDate.now());
        Domicilio dom = domicilioService.guardar(pac.toEntity().getDomicilio());
        pac.getDomicilio().setId(dom.getId());

        return new PacienteDTO(pacienteRepository.save(pac.toEntity()));
    }

    @Override
    public PacienteDTO buscar(Integer id) {
        return new PacienteDTO(pacienteRepository.findById(id).get());
    }

    @Override
    public List<PacienteDTO> buscarTodos() {
        List<PacienteDTO> pacientes = new ArrayList<>();

        for (Paciente pac:pacienteRepository.findAll()) {
            pacientes.add(new PacienteDTO(pac));
        }
        return pacientes;
    }

    @Override
    public PacienteDTO actualizar(PacienteDTO paciente) {
        Domicilio dom= domicilioService.guardar(paciente.getDomicilio().toEntity());
        paciente.getDomicilio().setId(dom.getId());

        return new PacienteDTO(pacienteRepository.save(paciente.toEntity()));
    }

    public PacienteDTO buscarPorDni(String dni){
        return new PacienteDTO(pacienteRepository.buscarPorDni(dni));
    }

    @Override
    //TENGO QUE AVERIGUAR QUE ES LO QUE RETORNA PARA VER COMO LO IMPLEMENTO
    public void eliminar(Integer id) {
        List<Paciente> pacientes = pacienteRepository.findAll();
        Paciente paciente =pacienteRepository.getById(id);
        Integer cont=0;
        for (Paciente pac:pacientes) {
            if(pac.getDomicilio().getId()==paciente.getDomicilio().getId()){ //comparamos si los id de los domicilios son iguales para saber si tienen el mismo
                cont++;
            }
        }
        if(cont==1){ //Si el contador es igual a 1 es porque el domicilio no se repetia
            pacienteRepository.deleteById(id);
            domicilioService.eliminar(paciente.getDomicilio().getId());
        }else{
            pacienteRepository.deleteById(id);
        }

    }
}
