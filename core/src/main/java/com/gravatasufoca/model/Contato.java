package com.gravatasufoca.model;

import org.codehaus.jackson.annotate.JsonBackReference;

import javax.persistence.*;
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
@Table(name = "tb_contato")
//@AttributeOverride(name = "id",column = @Column(name = "id_contato",nullable = false,unique = true))
public class Contato extends EntidadeBasica {

    @XmlElement
    private Integer id;
    @XmlElement
    private TipoContato tipoContato;
    @XmlElement
    private String contato;
    private Colaborador colaborador;

    @Transient
    private boolean excluido;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contato",nullable = false,unique = true)
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
    @JsonBackReference
    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    @Transient
    public boolean isExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }
}
