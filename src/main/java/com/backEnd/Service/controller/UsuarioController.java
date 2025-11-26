package com.backEnd.Service.controller;

import com.backEnd.Service.model.Usuario;
import com.backEnd.Service.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @GetMapping("/{correo}")
    public ResponseEntity<Usuario> getByCorreo(@PathVariable String correo) {
        try {
            Usuario u = usuarioService.findByCorreo(correo);
            u.setPassword(null); // don't return password
            return ResponseEntity.ok(u);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        try {
            if (usuarioService.existsByCorreo(usuario.getCorreo_usuario())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario ya existe");
            }
            Usuario saved = usuarioService.save(usuario);
            saved.setPassword(null);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{correo}")
    public ResponseEntity<Void> delete(@PathVariable String correo) {
        try {
            usuarioService.deleteById(correo);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{correo}")
    public ResponseEntity<?> update(@PathVariable String correo, @RequestBody Usuario usuario) {
        try {
            Usuario updated = usuarioService.update(correo, usuario);
            updated.setPassword(null);
            return ResponseEntity.ok(updated);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
