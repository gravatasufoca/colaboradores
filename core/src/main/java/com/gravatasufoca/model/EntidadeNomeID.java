package com.gravatasufoca.model;

/**
 * criado por bruno em 30/08/17.
 */
public abstract class EntidadeNomeID extends EntidadeBasica{
    protected Integer id;
    protected String nome;

    public void setId(Integer id) {
        this.id = id;
    }

    public abstract String getNome();

    public void setNome(String nome) {
        this.nome = nome;
    }
}
