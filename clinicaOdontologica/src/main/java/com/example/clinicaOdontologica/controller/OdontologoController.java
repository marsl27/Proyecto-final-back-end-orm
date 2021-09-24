package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.model.OdontologoDTO;
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
        OdontologoDTO odontologoCreado = odontologoService.guardar(odon);
        ResponseEntity<OdontologoDTO> respuesta;
        if(odontologoCreado != null){
            respuesta = ResponseEntity.ok(odontologoCreado);
        }else{
            respuesta = ResponseEntity.badRequest().body(odontologoCreado);
        }
        return respuesta;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<OdontologoDTO>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        OdontologoDTO odonBuscado = odontologoService.buscar(id);
        ResponseEntity<?> respuesta;
        if (odonBuscado != null) {
            respuesta= ResponseEntity.ok(odonBuscado);
        } else {
            respuesta= ResponseEntity.notFound().build();
        }
        return respuesta;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarOdontologo(@RequestBody OdontologoDTO odon){
        ResponseEntity<?> respuesta;
        if(odontologoService.buscar(odon.getId())!= null){
           respuesta = ResponseEntity.ok(odontologoService.actualizar(odon));
        }else {
          respuesta = ResponseEntity.badRequest().body("Servicio no disponible. Intente mas tarde");
        }
        return respuesta;
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
