package com.gravatasufoca.services;

import com.gravatasufoca.model.Contato;
import com.gravatasufoca.repositorios.Repositorio;
import com.gravatasufoca.repositorios.RepositorioContato;

import javax.inject.Inject;

/**
 * criado por bruno em 04/09/17.
 */
public class ContatoService extends AbstractService<Contato> {


    @Inject
    private RepositorioContato repositorio;

    public ContatoService() {
    }

    @Override
    protected Repositorio<Contato> getRepositorio() {
        return repositorio;
    }



}
