package com.gravatasufoca.repositorios;

import com.gravatasufoca.model.Unidade;

import java.util.List;

/**
 * criado por bruno em 30/08/17.
 */
public interface RepositorioUnidade extends Repositorio<Unidade> {
    List<Unidade> listar(String nome);
}
