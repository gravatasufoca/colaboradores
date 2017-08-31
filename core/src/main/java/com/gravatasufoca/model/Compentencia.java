package com.gravatasufoca.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * criado por bruno em 30/08/17.
 */
@Entity
@Table(name = "tb_competencia")
public class Compentencia extends EntidadeNomeID{

    private TipoCompetencia tipoCompetencia;
    private Colaborador colaborador;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_competencia",unique = true,nullable = false)
    public Integer getId() {
        return id;
    }

    @Override
    @Column(nullable = false,length = 250)
    public String getNome() {
        return nome;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_tipocompetencia",nullable = false)
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
