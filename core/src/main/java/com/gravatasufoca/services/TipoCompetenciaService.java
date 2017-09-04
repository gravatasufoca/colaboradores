package com.gravatasufoca.services;

import com.gravatasufoca.model.EntidadeBasica;
import com.gravatasufoca.model.TipoCompetencia;
import com.gravatasufoca.repositorios.Repositorio;
import com.gravatasufoca.repositorios.RepositorioTipoCompetencia;
import org.hibernate.criterion.Order;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * criado por bruno em 30/08/17.
 */
public class TipoCompetenciaService extends AbstractService<TipoCompetencia> implements IService{

    @Inject
    private RepositorioTipoCompetencia repositorio;

    public TipoCompetenciaService() {
    }

    @Override
    protected Repositorio<TipoCompetencia> getRepositorio() {
        return repositorio;
    }

    @Override
    public Map<String, String> salvar(EntidadeBasica entidade) {
        return super.salvar(entidade);
    }

    public List<TipoCompetencia> listar() {
        return repositorio.listar(Order.asc("nome"));
    }


}
