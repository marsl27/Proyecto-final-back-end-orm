package com.example.clinicaOdontologica.service.impl;

import com.example.clinicaOdontologica.persistence.entities.Domicilio;
import com.example.clinicaOdontologica.persistence.entities.Paciente;
import com.example.clinicaOdontologica.persistence.repositories.DomicilioRepository;
import com.example.clinicaOdontologica.persistence.repositories.PacienteRepository;
import com.example.clinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    DomicilioRepository domicilioRepository;

    public Paciente guardar(Paciente pac){//UNIR EL METODO CON BUSCARPORDNI PARA QUE PRIMERO BUSQUE EL PACIENTE Y SI NO ESTA, LO AGREGUE
        Paciente respuesta = null;
        pac.setFechaIngreso(LocalDate.now());
        Domicilio dom = domicilioRepository.buscarDomicilioPorCalleYNumero(pac.getDomicilio().getCalle(), pac.getDomicilio().getNumero());
        if(dom != null){
            pac.setDomicilio(dom);
        }
        return pacienteRepository.save(pac);
    }

    @Override
    public Paciente buscar(Integer id) {
        return pacienteRepository.findById(id).get();
    }

    @Override
    public List<Paciente> buscarTodos() {
        List<Paciente> pacientes = null;
        pacientes = pacienteRepository.findAll();
        return pacientes;
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        Domicilio dom= domicilioRepository.buscarDomicilioPorCalleYNumero(paciente.getDomicilio().getCalle(), paciente.getDomicilio().getNumero());
        if(dom != null){
            paciente.getDomicilio().setId(dom.getId());
        }
        return pacienteRepository.save(paciente);
    }

    public Paciente buscarPorDni(String dni){
        return pacienteRepository.buscarPorDni(dni);
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
        }else{ //Si el contador es mayor a uno, el domicilio se repite. Entonces borramos el paciente y guardamos el domicilio para los otros pacientes que lo tienen
            pacienteRepository.deleteById(id);
            domicilioRepository.save(paciente.getDomicilio());
        }
    }
}
