package com.gravatasufoca.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * criado por bruno em 30/08/17.
 */
@Entity
@Table(name = "tb_tipo_contato")
public class TipoContato extends EntidadeNomeID{

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tipocontato",unique = true,nullable = false)
    public Integer getId() {
        return id;
    }

    @Override
    @Column(nullable = false,length = 250)
    public String getNome() {
        return nome;
    }
}
