package com.gravatasufoca.services;

import com.gravatasufoca.model.Cargo;
import com.gravatasufoca.repositorios.Repositorio;
import com.gravatasufoca.repositorios.RepositorioCargo;
import org.hibernate.criterion.Order;

import javax.inject.Inject;
import java.util.List;

/**
 * criado por bruno em 04/09/17.
 */
public class CargoService extends AbstractService<Cargo> {

    @Inject
    private RepositorioCargo repositorio;

    public CargoService() {
    }

    @Override
    protected Repositorio<Cargo> getRepositorio() {
        return repositorio;
    }

    public List<Cargo> listar() {
        return repositorio.listar(Order.asc("nome"));
    }
}
