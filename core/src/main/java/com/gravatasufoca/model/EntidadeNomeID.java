package com.gravatasufoca.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * criado por bruno em 30/08/17.
 */
@MappedSuperclass
@XmlSeeAlso({
                Cargo.class, Unidade.class, TipoCompetencia.class, TipoContato.class
})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class EntidadeNomeID extends EntidadeBasica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column(nullable = false, length = 250)
    protected String nome;

    public void setId(Integer id) {
        this.id = id;
    }

    public abstract String getNome();

    public void setNome(String nome) {
        this.nome = nome;
    }
}
