package com.example.Curso_springboot.controllers;

import com.example.Curso_springboot.services.MsgService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class MsgController {

    private final MsgService msgService;

    public MsgController(MsgService msgService) {
        this.msgService = msgService;
    }

    @GetMapping("/mensagem")
    public String mensagem () {
        return msgService.obterMensagem();
    }
}
