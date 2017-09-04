package com.gravatasufoca.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * criado por bruno em 30/08/17.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "tb_colaborador")
public class Colaborador extends EntidadeBasica {

    private static final long serialVersionUID = 2886185930623763037L;
    @XmlElement
    private Integer id;
    @XmlElement
    private String nome;
    @XmlElement
    private String resumo;
    @XmlElement
    private String endereco;
    @XmlElement
    private Cargo cargo;
    @XmlElement
    private Unidade unidade;
    @XmlElementWrapper()
    @XmlElement(name="competencia")
    private List<Compentencia> competencias;
    @XmlElementWrapper()
    @XmlElement(name="contatos")
    private List<Contato> contatos;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_colaborador",unique = true,nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(nullable = false,length = 150)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    @Column(nullable = true,length = 4000)
    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    @Column(nullable = false,length = 500)
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_cargo",referencedColumnName = "id_cargo", nullable = false)
    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co_unidade",nullable = true)
    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    @OneToMany(mappedBy ="colaborador",fetch = FetchType.LAZY)
    public List<Compentencia> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(List<Compentencia> competencias) {
        this.competencias = competencias;
    }

    @OneToMany(mappedBy ="colaborador",fetch = FetchType.LAZY)
    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }


}
