package com.gravatasufoca.services;

import com.gravatasufoca.model.Unidade;
import com.gravatasufoca.repositorios.Repositorio;
import com.gravatasufoca.repositorios.RepositorioUnidade;
import org.hibernate.criterion.Order;

import javax.inject.Inject;
import java.util.List;

/**
 * criado por bruno em 04/09/17.
 */
public class UnidadeService extends AbstractService<Unidade> {


    @Inject
    private RepositorioUnidade repositorio;

    public UnidadeService() {
    }

    @Override
    protected Repositorio<Unidade> getRepositorio() {
        return repositorio;
    }

    public List<Unidade> listar() {
        return repositorio.listar(Order.asc("nome"));
    }

}
