package com.gravatasufoca.model;

import javax.persistence.*;

/**
 * criado por bruno em 30/08/17.
 */
@MappedSuperclass
public abstract class EntidadeNomeID extends EntidadeBasica{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column(nullable = false, length = 250)
    protected String nome;

    public void setId(Integer id) {
        this.id = id;
    }

    public abstract String getNome();

    public void setNome(String nome) {
        this.nome = nome;
    }
}
