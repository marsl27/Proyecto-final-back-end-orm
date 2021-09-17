package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.persistence.entities.Domicilio;
import com.example.clinicaOdontologica.persistence.entities.Paciente;
import com.example.clinicaOdontologica.service.PacienteService;
import com.example.clinicaOdontologica.service.impl.DomicilioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")

public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @PostMapping("/guardar")
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente pac){
       return ResponseEntity.ok(pacienteService.guardar(pac));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(pacienteService.buscar(id));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Paciente>> obtenerTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente pac){
        return ResponseEntity.ok(pacienteService.actualizar(pac));
    }

    @GetMapping("/buscar/{dni}")
    public ResponseEntity<Paciente> buscarPorDni(@PathVariable String dni){
        return ResponseEntity.ok(pacienteService.buscarPorDni(dni));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Integer id){
        ResponseEntity<String> respuesta =null;
        if(pacienteService.buscar(id) != null){
            pacienteService.eliminar(id);
            respuesta = ResponseEntity.ok("El paciente fue eliminado");
        }else{
            respuesta = ResponseEntity.notFound().build();
        }
        return respuesta;
    }
}
