package com.gravatasufoca.services;

import com.gravatasufoca.model.Colaborador;
import com.gravatasufoca.model.EntidadeBasica;
import com.gravatasufoca.repositorios.Repositorio;
import com.gravatasufoca.repositorios.RepositorioColaborador;
import com.gravatasufoca.repositorios.impl.RepositorioColaboradorImpl;

/**
 * criado por bruno em 30/08/17.
 */
public class ColaboradorService extends AbstractService<Colaborador> implements IService{

    private RepositorioColaborador repositorio = new RepositorioColaboradorImpl();

    public ColaboradorService() {
    }

    @Override
    protected Repositorio<Colaborador> getRepositorio() {
        return repositorio;
    }

    @Override
    public void salvar(EntidadeBasica entidade) {
        if(validarObrigatorios((Colaborador) entidade)){
            inserir((Colaborador) entidade);
        }
    }
}
