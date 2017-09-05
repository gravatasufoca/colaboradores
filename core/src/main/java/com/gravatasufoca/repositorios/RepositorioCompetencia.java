package com.gravatasufoca.repositorios;

import com.gravatasufoca.model.Colaborador;
import com.gravatasufoca.model.Compentencia;

/**
 * criado por bruno em 30/08/17.
 */
public interface RepositorioCompetencia extends Repositorio<Compentencia> {
    void excluir(Colaborador colaborador);
}
