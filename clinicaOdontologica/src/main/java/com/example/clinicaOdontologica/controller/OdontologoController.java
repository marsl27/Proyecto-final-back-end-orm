package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.persistence.entities.Odontologo;
import com.example.clinicaOdontologica.service.OdontologoService;
import com.example.clinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odontologos")
public class OdontologoController {

    @Autowired
    OdontologoService odontologoService;

    @PostMapping("/guardar")
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odon){
        return (ResponseEntity<Odontologo>) ResponseEntity.ok(odontologoService.guardar(odon));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Odontologo>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Integer id){
        return (ResponseEntity<Odontologo>) ResponseEntity.ok(odontologoService.buscar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odon, @PathVariable Integer id){
        return (ResponseEntity<Odontologo>) ResponseEntity.ok(odontologoService.actualizar(odon, id));
    }
}
