package com.gravatasufoca.model;

import javax.persistence.*;
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
@Table(name = "tb_cargo")
public class Cargo extends EntidadeNomeID {

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cargo",unique = true,nullable = false)
    @XmlElement
    public Integer getId() {
        return id;
    }
    @Override
    @Column(nullable = false,length = 250)
    @XmlElement
    public String getNome() {
        return nome;
    }
}
