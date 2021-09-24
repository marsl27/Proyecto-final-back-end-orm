package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.model.OdontologoDTO;
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
    public ResponseEntity<TurnoDTO> guardarTurno(@RequestBody TurnoDTO turno){
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
    public ResponseEntity<List<TurnoDTO>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
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
    public ResponseEntity<?> actualizarTurno(@RequestBody TurnoDTO turno){
        ResponseEntity<?> respuesta;
        if(turnoService.buscar(turno.getId())!= null){
            respuesta = ResponseEntity.ok(turnoService.actualizar(turno));
        }else {
            respuesta = ResponseEntity.badRequest().body("Servicio no disponible. Intente mas tarde");
        }
        return respuesta;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarTurno(@PathVariable Integer id){
        ResponseEntity<String> respuesta =null;
        if(turnoService.buscar(id)!= null){
            turnoService.eliminar(id);
            respuesta= ResponseEntity.ok("Turno eliminado");
        }else{
            respuesta= ResponseEntity.notFound().build();
        }
        return respuesta;
    }
}
