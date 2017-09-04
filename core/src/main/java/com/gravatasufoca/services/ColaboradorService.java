package com.gravatasufoca.services;

import com.gravatasufoca.model.Colaborador;
import com.gravatasufoca.model.EntidadeBasica;
import com.gravatasufoca.repositorios.Repositorio;
import com.gravatasufoca.repositorios.RepositorioColaborador;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * criado por bruno em 30/08/17.
 */
public class ColaboradorService extends AbstractService<Colaborador> implements IService{

    @Inject
    private RepositorioColaborador repositorio;

    public ColaboradorService() {
    }

    @Override
    protected Repositorio<Colaborador> getRepositorio() {
        return repositorio;
    }

    @Override
    public Map<String, String> salvar(EntidadeBasica entidade) {
        return super.salvar(entidade);
    }

    public List<Colaborador> consultar(String nome) {
        return repositorio.listarPorNome(nome);
    }
}
