package com.gravatasufoca.repositorios.impl;

import com.gravatasufoca.model.Colaborador;
import com.gravatasufoca.repositorios.RepositorioColaborador;
import com.gravatasufoca.repositorios.RepositorioGenerico;
import org.hibernate.Criteria;
import org.hibernate.sql.JoinType;

import java.util.List;

/**
 * criado por bruno em 30/08/17.
 */
public class RepositorioColaboradorImpl extends RepositorioGenerico<Colaborador> implements RepositorioColaborador {

    @Override
    public List<Colaborador> listarPorNome(String nome) {
        return listarPorAtributo("nome",nome);
    }

    public List<Colaborador> listar(Integer pagina){
        Criteria criteria=getSession().createCriteria(Colaborador.class);
        criteria.createAlias("cargo","cargo", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("unidade","unidade",JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("competencias","competencias",JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("contatos","contatos",JoinType.LEFT_OUTER_JOIN);

        criteria.setFirstResult(pagina*10-10==0?0:pagina*10-10);
        criteria.setMaxResults(pagina*10);
        return criteria.list();
    }
}
