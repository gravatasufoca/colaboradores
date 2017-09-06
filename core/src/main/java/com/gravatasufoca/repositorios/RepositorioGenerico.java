package com.gravatasufoca.repositorios;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import tools.devnull.trugger.reflection.Reflection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Implementao genrica de um repositrio.
 *
 * @param <E> Tipo da entidade.
 * @author bruno.canto
 */
public class RepositorioGenerico<E> implements Repositorio<E> {

    /**
     * Classe da entidade.
     */
    protected Class<E> entityClass;

    /**
     * EntityManager usado para a persistncia.
     */
    @Inject
    protected EntityManager entityManager;

    /**
     * Altera o EntityManager deste repositrio para o objeto dado.
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public RepositorioGenerico() {
        this.entityClass = Reflection.reflect().genericType("E").in(this);
    }

    /**
     * @return objeto Session do Hibernate referente ao {@link #entityManager}.
     */

    protected final Session getSession() {
        return (Session) entityManager.getDelegate();
    }

    public E obterPorId(Serializable id) {
        return entityManager.find(entityClass, id);
    }

    public List<E> listar() {
        return getSession().createCriteria(entityClass).list();
    }

    public List<E> listar(Order order) {
        return getSession().createCriteria(entityClass).addOrder(order).list();
    }

    public List<E> autocomplete(String campo, String termo) {
        return getSession().createCriteria(entityClass).add(Restrictions.like(campo, termo, MatchMode.START)).list();
    }

    public E atualizar(E entidade) {
        return entityManager.merge(entidade);
    }
    public void excluir(E entidade) {
        entityManager.remove(entidade);
    }
    public void excluir(Serializable id) {
        E obj = obterPorId(id);
        excluir(obj);
    }
    public void inserir(E entidade) {
        entityManager.persist(entidade);
    }
    public void inserirOuAtualizar(E entidade) {
        getSession().saveOrUpdate(entidade);
    }

    protected E recuperarPorAtributo(String nomeAtributo, Object valorAtributo) {
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.add(Restrictions.eq(nomeAtributo, valorAtributo));
        return (E) criteria.uniqueResult();
    }

    protected List<E> listarPorAtributo(String nomeAtributo, Object valorAtributo) {
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.add(Restrictions.eq(nomeAtributo, valorAtributo));
        return criteria.list();
    }

    protected List<E> listarPorAtributo(String nomeAtributo, Object valorAtributo, Order order) {
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.add(Restrictions.eq(nomeAtributo, valorAtributo)).addOrder(order);
        return criteria.list();
    }

}
