package com.example.Curso_springboot.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtil {

    // Em produção, injete via application.properties ou variável de ambiente
    private static final String SECRET = "umaStringBemLongaQueSuficienteParaHS256_1234567890";

    private static final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    // 1 dia
    private static final long EXPIRATION_TIME = 86400000;

    // Gera token
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, Jwts.SIG.HS256) // forma nova
                .compact();
    }

    // Extrai o "subject" do token (ex: username)
    public static String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // Valida token (checa assinatura e expiração)
    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)   // substitui o antigo setSigningKey
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false; // token inválido ou expirado
        }
    }
}
