package com.gravatasufoca.services;

import com.gravatasufoca.model.EntidadeBasica;
import com.gravatasufoca.repositorios.Repositorio;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * criado por bruno em 30/08/17.
 */
public abstract class AbstractService<E extends EntidadeBasica> implements Serializable {

    private static final long serialVersionUID = 8355454104338297239L;

    protected abstract Repositorio<E> getRepositorio();

    protected void inserir(E entidade) {
        if (getRepositorio() != null) {
            getRepositorio().inserir(entidade);
        }
    }

    protected void excluir(E entidade) {
        if (getRepositorio() != null) {
            getRepositorio().excluir(entidade);
        }
    }

    protected E recuperarPorId(Integer id) {
        if (getRepositorio() != null && id != null) {
            return (E) getRepositorio().obterPorId(id);
        }
        return null;
    }

    protected List<E> listar() {
        if (getRepositorio() != null) {
            return getRepositorio().listar();
        }
        return Collections.emptyList();
    }

    protected void atualizar(E entidade) {
        if (getRepositorio() != null) {
            getRepositorio().atualizar(entidade);
        }
    }

    protected boolean validarObrigatorios(E entidade) {

        if (entidade != null) {
            for (Method method : entidade.getClass().getDeclaredMethods()) {
                if (isRequired(method)) {
                    method.setAccessible(true);
                    try {
                        Object obj=method.invoke(entidade);
                        if(obj instanceof EntidadeBasica){
                            return validarObrigatorios((E) obj);
                        }
                        return !(obj==null);

                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    } finally {
                        method.setAccessible(false);
                    }
                }
            }
            return true;

        }
        return false;
    }

    private boolean isRequired(Method method) {
        Column coluna= method.getAnnotation(Column.class);
        if(coluna!=null){
            return coluna.nullable();
        }

        JoinColumn join= method.getAnnotation(JoinColumn.class);
        if(join!=null){
            return join.nullable();
        }

        return false;
    }

}
