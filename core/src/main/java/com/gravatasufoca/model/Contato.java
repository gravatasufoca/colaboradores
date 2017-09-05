package com.gravatasufoca.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 * criado por bruno em 30/08/17.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name = "tb_contato")
public class Contato extends EntidadeBasica {

    @XmlElement
    private Integer id;
    @XmlElement
    private TipoContato tipoContato;
    @XmlElement
    private String contato;
    @XmlTransient
    private Colaborador colaborador;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contato", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "co_tipocontato", nullable = false)
    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    @Column(nullable = false, length = 250)
    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
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
