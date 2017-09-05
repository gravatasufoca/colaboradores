package com.gravatasufoca.repositorios;

import com.gravatasufoca.model.Colaborador;

import java.util.List;

/**
 * criado por bruno em 30/08/17.
 */
public interface RepositorioColaborador extends Repositorio<Colaborador> {
    List<Colaborador> listarPorNome(String nome);
    List<Colaborador> listar(Integer pagina);
}
