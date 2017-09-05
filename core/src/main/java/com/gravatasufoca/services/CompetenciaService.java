package com.gravatasufoca.services;

import com.gravatasufoca.model.Colaborador;
import com.gravatasufoca.model.Compentencia;
import com.gravatasufoca.repositorios.Repositorio;
import com.gravatasufoca.repositorios.RepositorioCompetencia;

import javax.inject.Inject;

/**
 * criado por bruno em 04/09/17.
 */
public class CompetenciaService extends AbstractService<Compentencia> {


    @Inject
    private RepositorioCompetencia repositorio;

    public CompetenciaService() {
    }

    @Override
    protected Repositorio<Compentencia> getRepositorio() {
        return repositorio;
    }

    public void excluir(Colaborador colaborador){
        repositorio.excluir(colaborador);
    }
}
