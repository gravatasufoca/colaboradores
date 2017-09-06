package com.gravatasufoca.repositorios.impl;

import com.gravatasufoca.model.Colaborador;
import com.gravatasufoca.repositorios.RepositorioColaborador;
import com.gravatasufoca.repositorios.RepositorioGenerico;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * criado por bruno em 30/08/17.
 */
public class RepositorioColaboradorImpl extends RepositorioGenerico<Colaborador> implements RepositorioColaborador {

    @Override
    public List<Colaborador> listarPorNome(String nome) {
        Criteria criteria=getCriteria();
        criteria.add(Restrictions.like("nome",nome));
        return criteria.list();
    }

    public List<Colaborador> listar(Integer pagina){
        Criteria criteria=getCriteria();
        criteria.setFirstResult(pagina*10-10==0?0:pagina*10-10);

        criteria.setMaxResults(pagina*10);
        criteria.addOrder(Order.asc("nome"));
        return criteria.list();
    }

    @Override
    public Colaborador recuperarColaborador(@NotNull Integer id) {
        Criteria criteria=getCriteria();
        criteria.add(Restrictions.idEq(id));
        try {
            return (Colaborador) criteria.uniqueResult();
        }catch (Exception e){
            return null;
        }
    }

    private Criteria getCriteria(){
        Criteria criteria=getSession().createCriteria(Colaborador.class);
        criteria.createAlias("cargo","cargo", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("unidade","unidade",JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("competencias","competencias",JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("contatos","contatos",JoinType.LEFT_OUTER_JOIN);

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return criteria;
    }
}
