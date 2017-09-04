package com.gravatasufoca.model;

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
public class Unidade extends EntidadeNomeID{

    @Override
    @Column(name = "id_unidade",unique = true,nullable = false)
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
