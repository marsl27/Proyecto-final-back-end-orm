package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.exceptions.ServiceBadRequestException;
import com.example.clinicaOdontologica.exceptions.ServiceNotFoundException;
import com.example.clinicaOdontologica.model.TurnoDTO;
import com.example.clinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    TurnoService turnoService;

    @PostMapping("/guardar")
    public ResponseEntity<TurnoDTO> guardarTurno(@RequestBody TurnoDTO turno) throws ServiceNotFoundException, ServiceBadRequestException {
        TurnoDTO turnoCreado = turnoService.guardar(turno);
        ResponseEntity<TurnoDTO> respuesta;
        if(turnoCreado != null){
            respuesta = ResponseEntity.ok(turnoCreado);
        }else{
            respuesta = ResponseEntity.badRequest().body(turnoCreado);
        }
        return respuesta;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TurnoDTO>> buscarTodos() throws ServiceNotFoundException, ServiceBadRequestException {
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) throws ServiceNotFoundException, ServiceBadRequestException {
        TurnoDTO turnoBuscado = turnoService.buscar(id);
        ResponseEntity<?> respuesta;
        if (turnoBuscado != null) {
            respuesta= ResponseEntity.ok(turnoBuscado);
        } else {
            respuesta= ResponseEntity.notFound().build();
        }
        return respuesta;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarTurno(@RequestBody TurnoDTO turno) throws ServiceBadRequestException, ServiceNotFoundException{
        turnoService.actualizar(turno);
        return ResponseEntity.ok("El turno fue actualizado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarTurno(@PathVariable Integer id) throws ServiceNotFoundException, ServiceBadRequestException {
        turnoService.eliminar(id);

        return ResponseEntity.ok("Turno eliminado");
    }
}
