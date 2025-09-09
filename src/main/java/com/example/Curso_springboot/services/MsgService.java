package com.example.Curso_springboot.services;

import com.example.Curso_springboot.repositories.MsgRepository;
import org.springframework.stereotype.Service;

@Service
public class MsgService {

    public final MsgRepository msgRepository;

    public MsgService(MsgRepository msgRepository) {
        this.msgRepository = msgRepository;
    }

    public String obterMensagem () {
        return msgRepository.obterMensagem();
    }
}
