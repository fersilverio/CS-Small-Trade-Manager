package com.uem.simple.manager.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cliente {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    private String rg;

    private String cpf;

    private String nascimento;

    private String email;

    @Embedded
    private Endereco endereco;

    private String telefone;

    private String celular;

}
