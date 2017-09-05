package com.gravatasufoca.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * criado por bruno em 30/08/17.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "tb_unidade")
@AttributeOverride(name = "id",column = @Column(name = "id_unidade",nullable = false,unique = true))
public class Unidade extends EntidadeNomeID{

    @Override
    @XmlElement
    public Integer getId() {
        return id;
    }

    @Override
    @XmlElement
    public String getNome() {
        return nome;
    }
}
