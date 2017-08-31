package com.gravatasufoca.model;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * criado por bruno em 30/08/17.
 * Classe abstrata para ser herdada pelas Entidades da aplicação
 *
 *
 */
@MappedSuperclass
public abstract class EntidadeBasica implements Serializable {

    private static final long serialVersionUID = 7086808939746169469L;


    /**
     * Obtém o identificador da entidade.
     *
     * @return identificador da entidade.
     */
    public abstract Integer getId();



}