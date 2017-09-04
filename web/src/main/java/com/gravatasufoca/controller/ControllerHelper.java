package com.gravatasufoca.controller;

import com.gravatasufoca.model.RespostaEntity;

import java.util.List;

/**
 * criado por bruno em 03/09/17.
 */
public abstract class ControllerHelper {

    protected RespostaEntity criaMensagemResposta(List<String> mensagens,Object objeto){
        return new RespostaEntity.RespostaEntityBuilder().setMensagens(mensagens).setObjeto(objeto).build();
    }
}
