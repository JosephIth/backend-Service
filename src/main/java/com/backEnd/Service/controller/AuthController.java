package com.backEnd.Service.controller;

import com.backEnd.Service.model.Usuario;
import com.backEnd.Service.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario body) {
        try {
            Usuario user = usuarioService.authenticate(body.getCorreo_usuario(), body.getPassword());
            if (user == null) return ResponseEntity.status(401).body("Credenciales invalidas");
            user.setPassword(null);
            return ResponseEntity.ok(user);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(401).body("Usuario no encontrado o credenciales invalidas");
        }
    }
}
