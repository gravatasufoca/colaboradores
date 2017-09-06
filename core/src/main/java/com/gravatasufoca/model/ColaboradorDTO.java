package com.gravatasufoca.model;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

/**
 * criado por bruno em 06/09/17.
 */
public class ColaboradorDTO {

    @FormParam("colaborador")
    @PartType(MediaType.APPLICATION_JSON)
    private Colaborador colaborador;

    @FormParam("imagem")
    private byte[] imagem;

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
