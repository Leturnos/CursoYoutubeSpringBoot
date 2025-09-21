package com.example.Curso_springboot.services;

import com.example.Curso_springboot.models.Usuarios;
import com.example.Curso_springboot.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServices {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServices(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuarios registrarUsuario(String username, String password) {
        String senhaCriptografada = passwordEncoder.encode(password);
        Usuarios usuarios = new Usuarios(username, senhaCriptografada);
        return usuarioRepository.save(usuarios);
    }

    public Optional<Usuarios> buscarUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public boolean validarSenha(String senhaPura, String senhaCriptografada) {
        return passwordEncoder.matches(senhaPura, senhaCriptografada);
    }
}
