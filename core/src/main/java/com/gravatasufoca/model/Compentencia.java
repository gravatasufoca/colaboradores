package com.gravatasufoca.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 * criado por bruno em 30/08/17.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name = "tb_competencia")
public class Compentencia extends EntidadeBasica{

    @XmlElement
    private Integer id;
    @XmlElement
    private TipoCompetencia tipoCompetencia;
    @XmlTransient
    private Colaborador colaborador;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_competencia",nullable = false,unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "co_tipocompetencia", nullable = false)
    public TipoCompetencia getTipoCompetencia() {
        return tipoCompetencia;
    }

    public void setTipoCompetencia(TipoCompetencia tipoCompetencia) {
        this.tipoCompetencia = tipoCompetencia;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @XmlTransient
    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
}
