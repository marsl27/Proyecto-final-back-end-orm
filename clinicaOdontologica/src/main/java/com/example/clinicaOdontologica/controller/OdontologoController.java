package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.model.OdontologoDTO;
import com.example.clinicaOdontologica.persistence.entities.Odontologo;
import com.example.clinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odontologos")
public class OdontologoController {

    @Autowired
    OdontologoService odontologoService;

    @PostMapping("/guardar")
    public ResponseEntity<OdontologoDTO> guardarOdontologo(@RequestBody OdontologoDTO odon){
        return ResponseEntity.ok(odontologoService.guardar(odon));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<OdontologoDTO>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDTO> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(odontologoService.buscar(id));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarOdontologo(@RequestBody OdontologoDTO odon){
        if(odontologoService.buscar(odon.getId())!= null){
            return ResponseEntity.ok(odontologoService.actualizar(odon));
        }else {
            return ResponseEntity.badRequest().body("Servicio no disponible. Intente mas tarde");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarOdontologo(@PathVariable Integer id){
        ResponseEntity<String> respuesta =null;
        if(odontologoService.buscar(id)!= null){
            odontologoService.eliminar(id);
            respuesta= ResponseEntity.ok("Odontologo eliminado");
        }else{
            respuesta= ResponseEntity.notFound().build();
        }
        return respuesta;
    }
}
