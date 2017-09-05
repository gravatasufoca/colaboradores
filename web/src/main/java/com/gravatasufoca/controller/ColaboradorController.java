package com.gravatasufoca.controller;

import com.gravatasufoca.model.Colaborador;
import com.gravatasufoca.services.ColaboradorService;
import com.sun.istack.internal.Nullable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * criado por bruno em 30/08/17.
 */
@RequestScoped
@Path(value = "/colaborador")
public class ColaboradorController extends ControllerHelper {

    @Inject
    private ColaboradorService colaboradorService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response salvar(Colaborador colaborador) {
        colaboradorService.salvar(colaborador);
        return criaMensagemResposta(colaboradorService.getErros(), colaborador);
    }

    @GET()
    @Path(value = "consulta")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response consultar(@Nullable String nome) {
        return criaMensagemResposta(colaboradorService.getErros(), colaboradorService.consultar(nome));
    }



}
