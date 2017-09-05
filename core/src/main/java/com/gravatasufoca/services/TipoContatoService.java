package com.gravatasufoca.services;

import com.gravatasufoca.model.EntidadeBasica;
import com.gravatasufoca.model.TipoContato;
import com.gravatasufoca.repositorios.Repositorio;
import com.gravatasufoca.repositorios.RepositorioTipoContato;
import org.hibernate.criterion.Order;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * criado por bruno em 30/08/17.
 */
public class TipoContatoService extends AbstractService<TipoContato> implements IService{

    @Inject
    private RepositorioTipoContato repositorio;

    public TipoContatoService() {
    }

    @Override
    protected Repositorio<TipoContato> getRepositorio() {
        return repositorio;
    }

    @Override
    public Map<String, String> salvar(EntidadeBasica entidade) {
        return super.salvar(entidade);
    }

    public List<TipoContato> listar() {
        return repositorio.listar(Order.asc("nome"));
    }


}
