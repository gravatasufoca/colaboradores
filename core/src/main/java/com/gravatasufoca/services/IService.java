package com.gravatasufoca.services;

import com.gravatasufoca.model.EntidadeBasica;

import java.util.Map;

/**
 * criado por bruno em 31/08/17.
 */
public interface IService<E extends EntidadeBasica> {
    Map<String, String> salvar(E entidade);
}
