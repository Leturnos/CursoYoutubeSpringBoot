package com.example.Curso_springboot.controllers;

import com.example.Curso_springboot.models.Usuarios;
import com.example.Curso_springboot.security.JwtUtil;
import com.example.Curso_springboot.services.UsuarioServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioServices usuarioServices;

    public AuthController(UsuarioServices usuarioServices) {
        this.usuarioServices = usuarioServices;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        Usuarios usuario = usuarioServices.registrarUsuario(request.get("username"), request.get("password"));
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        Optional<Usuarios> usuarios = usuarioServices.buscarUsername(request.get("username"));
        if (usuarios.isPresent() && usuarios.get().getPassword().equals(request.get("password"))) {
            String token = JwtUtil.generateToken(usuarios.get().getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
}
