package com.gravatasufoca.repositorios.impl;

import com.gravatasufoca.model.Unidade;
import com.gravatasufoca.repositorios.RepositorioGenerico;
import com.gravatasufoca.repositorios.RepositorioUnidade;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * criado por bruno em 30/08/17.
 */
public class RepositorioUnidadeImpl extends RepositorioGenerico<Unidade> implements RepositorioUnidade{

    @Override
    public List<Unidade> listar(String nome) {
        Criteria criteria=getSession().createCriteria(Unidade.class);
        criteria.add(Restrictions.ilike("nome",nome+"%"));
        criteria.addOrder(Order.asc("nome"));
        return criteria.list();
    }
}
