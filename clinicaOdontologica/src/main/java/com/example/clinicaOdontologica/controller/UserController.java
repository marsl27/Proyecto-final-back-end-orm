package com.example.clinicaOdontologica.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public String home(){
        return "<h1>Bienvenidos a la clínica odontológica</h1>";
    }

    @GetMapping("/user")
    public String user(){
        return "<h1>Bienvenido usuario</h1>";
    }

    @GetMapping("/admin")
    public String admin(){
        return "<h1>Bienvenido admin</h1>";
    }
}
