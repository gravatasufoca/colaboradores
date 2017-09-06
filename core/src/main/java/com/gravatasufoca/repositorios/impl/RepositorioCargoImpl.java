package com.gravatasufoca.repositorios.impl;

import com.gravatasufoca.model.Cargo;
import com.gravatasufoca.repositorios.RepositorioCargo;
import com.gravatasufoca.repositorios.RepositorioGenerico;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * criado por bruno em 30/08/17.
 */
public class RepositorioCargoImpl extends RepositorioGenerico<Cargo> implements RepositorioCargo {

    @Override
    public List<Cargo> listar(String nome) {
        Criteria criteria=getSession().createCriteria(Cargo.class);
        criteria.add(Restrictions.ilike("nome",nome+"%"));
        criteria.addOrder(Order.asc("nome"));
        return criteria.list();
    }
}
