package com.gravatasufoca.controller;

import com.gravatasufoca.services.CargoService;
import com.gravatasufoca.services.TipoCompetenciaService;
import com.gravatasufoca.services.TipoContatoService;
import com.gravatasufoca.services.UnidadeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * criado por bruno em 04/09/17.
 */
@RequestScoped
@Path(value = "/apoio")
public class ApoioController extends ControllerHelper {

    @Inject
    private TipoCompetenciaService tipoCompetenciaService;

    @Inject
    private TipoContatoService tipoContatoService;

    @Inject
    private CargoService cargoService;

    @Inject
    private UnidadeService unidadeService;

    @GET()
    @Path(value = "tipoCompetencias")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response listarTiposCompetencias() {
        return criaMensagemResposta(tipoCompetenciaService.getErros(), tipoCompetenciaService.listar());
    }

    @GET()
    @Path(value = "tipoContatos")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response listarTiposContatos() {
        return criaMensagemResposta(tipoContatoService.getErros(), tipoContatoService.listar());
    }

    @GET()
    @Path(value = "cargos/{nome}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response listarCargos(@PathParam("nome")String nome) {
        return criaMensagemResposta(tipoContatoService.getErros(), cargoService.listar(nome));
    }

    @GET()
    @Path(value = "unidades")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response listarUnidades() {
        return criaMensagemResposta(tipoContatoService.getErros(), unidadeService.listar());
    }
}
