package com.gravatasufoca.controller;

import com.gravatasufoca.model.RespostaEntity;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * criado por bruno em 03/09/17.
 */
public abstract class ControllerHelper {

    //TODO: FAZER UMA FACTORY PARA O RESPOSTA
    protected Response criaMensagemResposta(String msgSucesso,Map<String,String> mensagens,Object objeto) {
        return Response.ok(new RespostaEntity.RespostaEntityBuilder().setMensagens(mensagens).setObjeto(objeto).setMensagemSucesso(msgSucesso).build(), MediaType.APPLICATION_JSON).build();
    }

    protected Response criaMensagemResposta(Map<String,String> mensagens,Object objeto){
        return Response.ok(new RespostaEntity.RespostaEntityBuilder().setMensagens(mensagens).setObjeto(objeto).build(), MediaType.APPLICATION_JSON).build();
    }
}
