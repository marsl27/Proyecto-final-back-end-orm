package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.exceptions.ServiceBadRequestException;
import com.example.clinicaOdontologica.exceptions.ServiceNotFoundException;
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
    public ResponseEntity<PacienteDTO> guardarPaciente(@RequestBody PacienteDTO pac) throws ServiceNotFoundException, ServiceBadRequestException {
        PacienteDTO pacCreado = pacienteService.guardar(pac);
        ResponseEntity<PacienteDTO> respuesta;
        if(pacCreado != null){
            respuesta = ResponseEntity.ok(pacCreado);
        }else{
            respuesta = ResponseEntity.badRequest().body(pacCreado);
        }
        return respuesta;
    }
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Integer id) throws ServiceNotFoundException, ServiceBadRequestException{
        PacienteDTO pacBuscado = pacienteService.buscar(id);
        ResponseEntity<PacienteDTO> respuesta;
        if (pacBuscado != null) {
            respuesta= ResponseEntity.ok(pacBuscado);
        } else {
            respuesta= ResponseEntity.notFound().build();
        }
        return respuesta;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PacienteDTO>> obtenerTodos() throws ServiceNotFoundException, ServiceBadRequestException {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarPaciente(@RequestBody PacienteDTO pac)throws ServiceNotFoundException, ServiceBadRequestException{
      pacienteService.actualizar(pac);

      return ResponseEntity.ok("El paciente fue actualizado");
    }

    @GetMapping("/buscar/{dni}")
    public ResponseEntity<PacienteDTO> buscarPorDni(@PathVariable Integer dni) throws ServiceBadRequestException, ServiceNotFoundException{
        PacienteDTO pacBuscado = pacienteService.buscarPorDni(dni);

        return ResponseEntity.ok(pacBuscado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Integer id) throws ServiceNotFoundException, ServiceBadRequestException {

        pacienteService.eliminar(id);

        return ResponseEntity.ok("El paciente fue eliminado");
    }
}
