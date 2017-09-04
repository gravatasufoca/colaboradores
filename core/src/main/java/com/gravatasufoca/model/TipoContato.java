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
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name = "tb_tipo_contato")
public class TipoContato extends EntidadeNomeID{

    @Override
    @Column(name = "id_tipocontato",unique = true,nullable = false)
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
