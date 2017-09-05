package com.gravatasufoca.factories;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;

@RequestScoped
public class EntityManagerProducer implements Serializable {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("testecolobaradorPU");
    private EntityManager entityManager = factory.createEntityManager();

    @Produces
    @RequestScoped
    public EntityManager criaEntityManager(){
        return entityManager;
    }

    public void dispose(@Disposes EntityManager em){
        em.close();
    }

}