package com.gravatasufoca.repositorios.impl;

import com.gravatasufoca.model.Colaborador;
import com.gravatasufoca.repositorios.RepositorioColaborador;
import com.gravatasufoca.repositorios.RepositorioGenerico;

import java.util.List;

/**
 * criado por bruno em 30/08/17.
 */
public class RepositorioColaboradorImpl extends RepositorioGenerico<Colaborador> implements RepositorioColaborador {

    @Override
    public List<Colaborador> listarPorNome(String nome) {
        return listarPorAtributo("nome",nome);
    }
}
