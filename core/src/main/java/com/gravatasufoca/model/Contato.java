package com.gravatasufoca.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * criado por bruno em 30/08/17.
 */
@Entity
@Table(name = "tb_contato")
public class Contato extends EntidadeBasica{

    private Integer id;
    private TipoContato tipoContato;
    private String contato;
    private Colaborador colaborador;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_contato",unique = true,nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_tipocontato",nullable = false)
    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }
    @Column(nullable = false,length = 250)
    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
}
