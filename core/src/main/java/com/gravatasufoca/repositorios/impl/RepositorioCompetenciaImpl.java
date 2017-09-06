package com.gravatasufoca.repositorios.impl;

import com.gravatasufoca.model.Colaborador;
import com.gravatasufoca.model.Compentencia;
import com.gravatasufoca.repositorios.RepositorioCompetencia;
import com.gravatasufoca.repositorios.RepositorioGenerico;

/**
 * criado por bruno em 30/08/17.
 */
public class RepositorioCompetenciaImpl extends RepositorioGenerico<Compentencia> implements RepositorioCompetencia{

    @Override
    public void excluir(Colaborador colaborador) {
        if(colaborador!=null && colaborador.getId()!=null) {
            entityManager.createQuery("delete from Compentencia where colaborador=:colaborador").setParameter("colaborador",colaborador).executeUpdate();

        }
    }
}
