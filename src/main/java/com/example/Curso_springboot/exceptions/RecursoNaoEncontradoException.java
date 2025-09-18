package com.example.Curso_springboot.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException{
    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
