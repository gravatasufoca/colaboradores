package com.gravatasufoca.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * criado por bruno em 30/08/17.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "tb_competencia")
public class Compentencia extends EntidadeNomeID{

    private TipoCompetencia tipoCompetencia;
    private Colaborador colaborador;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_competencia",unique = true,nullable = false)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_tipocompetencia",nullable = false)
    @XmlElement
    public TipoCompetencia getTipoCompetencia() {
        return tipoCompetencia;
    }

    public void setTipoCompetencia(TipoCompetencia tipoCompetencia) {
        this.tipoCompetencia = tipoCompetencia;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
}
