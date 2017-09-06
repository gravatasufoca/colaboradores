package com.gravatasufoca.repositorios;

import com.gravatasufoca.model.Cargo;

import java.util.List;

/**
 * criado por bruno em 30/08/17.
 */
public interface RepositorioCargo extends Repositorio<Cargo> {

    List<Cargo> listar(String nome);
}
