package com.gravatasufoca.controller;

import com.gravatasufoca.model.Cargo;
import com.gravatasufoca.model.Colaborador;
import com.gravatasufoca.services.ColaboradorService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * criado por bruno em 30/08/17.
 */
@RequestScoped
@Path(value = "/colaborador")
public class ColaboradorController {

    @Inject
    private ColaboradorService colaboradorService;

    @GET()
    @Path(value = "teste")
    public void teste(){
        Colaborador colaborador=new Colaborador();
        colaborador.setNome("Bruno");
        colaborador.setEndereco("teste");
        colaborador.setCargo(new Cargo());
        colaboradorService.salvar(colaborador);
    }
}
