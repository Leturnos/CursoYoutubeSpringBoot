package com.example.Curso_springboot.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class MsgRepository {

    public String obterMensagem () {
        return "Olá do repositorio";
    }
}
