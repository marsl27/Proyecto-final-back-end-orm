package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.persistence.entities.Domicilio;
import com.example.clinicaOdontologica.service.impl.DomicilioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {

    @Autowired
    DomicilioServiceImpl domicilioService;

    @GetMapping("/todos")
    public ResponseEntity<List<Domicilio>> getAll(){
        return ResponseEntity.ok(domicilioService.buscarTodos());
    }
}
