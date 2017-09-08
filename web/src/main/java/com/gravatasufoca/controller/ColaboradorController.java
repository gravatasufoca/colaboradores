package com.gravatasufoca.controller;

import com.gravatasufoca.interceptor.Autenticado;
import com.gravatasufoca.model.Colaborador;
import com.gravatasufoca.model.ColaboradorDTO;
import com.gravatasufoca.services.ColaboradorService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * criado por bruno em 30/08/17.
 */
@Autenticado
@Stateless
@Path(value = "/colaborador")
public class ColaboradorController extends ControllerHelper {

    @Inject
    private ColaboradorService colaboradorService;

    @GET
    @Path("{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response recuperarColaborador(@PathParam("id") Integer id) {
        return criaMensagemResposta(colaboradorService.getErros(), colaboradorService.recuperarColaborador(id));
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response salvar(@MultipartForm ColaboradorDTO colaboradorDTO) {
        Colaborador colaborador = colaboradorDTO.getColaborador();
        colaborador.setAvatar(colaboradorDTO.getImagem());

        Map<String, String> erros = colaboradorService.salvar(colaborador);
        if (erros.isEmpty()) {
            return criaMensagemResposta("Salvo com sucesso!", colaboradorService.getErros(), colaborador);
        }
        return criaMensagemResposta(colaboradorService.getErros(), colaborador);
    }

    @GET()
    @Path(value = "/pagina/{pagina}/consulta/{nome}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response consultar(@PathParam("nome") String nome, @PathParam("pagina") Integer pagina) {
        return criaMensagemResposta(colaboradorService.getErros(), "null".equalsIgnoreCase(nome) ? colaboradorService.listar(pagina) : colaboradorService.consultar(nome));
    }

}
