package com.gravatasufoca.services;

import com.gravatasufoca.model.EntidadeBasica;
import com.gravatasufoca.repositorios.Repositorio;

import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * criado por bruno em 30/08/17.
 */
@Stateless
public abstract class AbstractService<E extends EntidadeBasica> implements Serializable {

    private static final long serialVersionUID = 8355454104338297239L;
    private final String MSG_OBRIGATORIO = "MSG_001";

    protected Map<String, String> erros = new HashMap<>();

    protected abstract Repositorio<E> getRepositorio();

    public Map<String, String> salvar(EntidadeBasica entidade) {
        if(validarObrigatorios((E) entidade)){
            inserir((E) entidade);
        }
        return erros;
    }

    protected void inserir(E entidade) {
        if (getRepositorio() != null) {
            getRepositorio().inserirOuAtualizar(entidade);
        }
    }

    protected void excluir(E entidade) {
        if (getRepositorio() != null && entidade!=null && entidade.getId()!=null) {
            getRepositorio().excluir(entidade.getId());
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
        return validarObrigatorios(entidade,null);
    }

    private boolean validarObrigatorios(E entidade, Method pai) {

        if (entidade != null) {
            boolean check = true;
            for (Method method : entidade.getClass().getDeclaredMethods()) {
                if (isRequired(method)) {
                    method.setAccessible(true);
                    try {
                        Object obj = method.invoke(entidade);
                        if (obj instanceof EntidadeBasica) {
                            if (!validarObrigatorios((E) obj, method)) {
                                check = false;
                                addErro(method, pai);
                            }
                        } else if (obj == null) {
                            check = false;
                            addErro(method, pai);
                        }

                    } catch (IllegalAccessException | InvocationTargetException e) {

                    } finally {
                        method.setAccessible(false);
                    }
                }
            }
            return check;

        }
        return false;
    }

    private void addErro(Method metodo, Method pai) {
        erros.put(pai != null ? pai.getName().replace("get", "") + "#" + metodo.getName().replace("get", "") : metodo.getName().replace("get", ""), MSG_OBRIGATORIO);
    }

    private boolean isRequired(Method method) {
        if (method.isAnnotationPresent(Id.class)) {
            return false;
        }
        Column coluna = method.getAnnotation(Column.class);
        if (coluna != null) {
            return !coluna.nullable();
        }

        JoinColumn join = method.getAnnotation(JoinColumn.class);
        if (join != null) {
            return !join.nullable();
        }

        return false;
    }

    public Map<String, String> getErros() {
        return erros;
    }
}
