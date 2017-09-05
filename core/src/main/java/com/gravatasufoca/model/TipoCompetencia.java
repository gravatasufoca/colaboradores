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
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name = "tb_tipo_competencia")
@AttributeOverride(name = "id",column = @Column(name = "id_tipocompetencia",nullable = false,unique = true))
public class TipoCompetencia extends EntidadeNomeID {

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
