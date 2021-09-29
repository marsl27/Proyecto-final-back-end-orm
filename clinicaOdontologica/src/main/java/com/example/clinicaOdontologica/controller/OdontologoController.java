package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.exceptions.ServiceBadRequestException;
import com.example.clinicaOdontologica.exceptions.ServiceNotFoundException;
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
    public ResponseEntity<OdontologoDTO> guardarOdontologo(@RequestBody OdontologoDTO odon) throws ServiceNotFoundException, ServiceBadRequestException {
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
    public ResponseEntity<List<OdontologoDTO>> buscarTodos() throws ServiceNotFoundException, ServiceBadRequestException {
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) throws ServiceNotFoundException, ServiceBadRequestException {
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
    public ResponseEntity<?> actualizarOdontologo(@RequestBody OdontologoDTO odon) throws ServiceNotFoundException, ServiceBadRequestException {
        odontologoService.actualizar(odon);
        return ResponseEntity.ok("El odontólogo fue actualizado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarOdontologo(@PathVariable Integer id) throws ServiceNotFoundException, ServiceBadRequestException {
        odontologoService.eliminar(id);
        return ResponseEntity.ok("Odontologo eliminado");
    }
}
