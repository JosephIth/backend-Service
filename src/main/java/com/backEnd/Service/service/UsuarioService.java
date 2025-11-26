package com.backEnd.Service.service;

import com.backEnd.Service.model.Usuario;
import com.backEnd.Service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findByCorreo(String correo) {
        return usuarioRepository.findById(correo).orElseThrow(NoSuchElementException::new);
    }

    public Usuario save(Usuario usuario) {
        if (usuario.getPassword() != null) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario update(String correo, Usuario usuario) {
        Usuario existing = usuarioRepository.findById(correo).orElseThrow(NoSuchElementException::new);
        if (usuario.getNombre_usuario() != null) existing.setNombre_usuario(usuario.getNombre_usuario());
        if (usuario.getImg_url() != null) existing.setImg_url(usuario.getImg_url());
        if (usuario.getIsAdmin() != null) existing.setIsAdmin(usuario.getIsAdmin());
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            existing.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        return usuarioRepository.save(existing);
    }

    public void deleteById(String correo) {
        usuarioRepository.deleteById(correo);
    }

    public Usuario authenticate(String correo, String rawPassword) {
        Usuario u = usuarioRepository.findById(correo).orElseThrow(NoSuchElementException::new);
        if (passwordEncoder.matches(rawPassword, u.getPassword())) {
            return u;
        }
        return null;
    }

    public boolean existsByCorreo(String correo) {
        return usuarioRepository.existsById(correo);
    }

}
