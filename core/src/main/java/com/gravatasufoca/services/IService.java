package com.gravatasufoca.services;

import com.gravatasufoca.model.EntidadeBasica;

/**
 * criado por bruno em 31/08/17.
 */
public interface IService<E extends EntidadeBasica> {
    void salvar(E entidade);
}
