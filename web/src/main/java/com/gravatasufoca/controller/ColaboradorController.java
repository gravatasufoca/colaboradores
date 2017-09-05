package com.gravatasufoca.controller;

import com.gravatasufoca.model.Colaborador;
import com.gravatasufoca.services.ColaboradorService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

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
    @Path(value = "/pagina/{pagina}/consulta/{nome}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response consultar(@PathParam("nome") String nome, @PathParam("pagina") Integer pagina) {
        return criaMensagemResposta(colaboradorService.getErros(),"null".equalsIgnoreCase(nome)?colaboradorService.listar(pagina): colaboradorService.consultar(nome));
    }

}
