package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.model.PacienteDTO;
import com.example.clinicaOdontologica.service.PacienteService;
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
    public ResponseEntity<PacienteDTO> guardarPaciente(@RequestBody PacienteDTO pac){
       return ResponseEntity.ok(pacienteService.guardar(pac));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(pacienteService.buscar(id));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PacienteDTO>> obtenerTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarPaciente(@RequestBody PacienteDTO pac){
        if(pacienteService.buscar(pac.getId())!= null){
            return ResponseEntity.ok(pacienteService.actualizar(pac));
        }else {
            return ResponseEntity.badRequest().body("Servicio no disponible. Intente mas tarde");
        }
    }

    @GetMapping("/buscar/{dni}")
    public ResponseEntity<PacienteDTO> buscarPorDni(@PathVariable String dni){
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
