package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.service.auth.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    AppUserService userService;

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





    /*@GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new AppUser());

        return "signup_form";
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody AppUser usuario) throws UserAlreadyExistException {
        userService.processRegister(usuario);
        return ResponseEntity.ok("El usuario fue creado correctamente");
    }*/
}
