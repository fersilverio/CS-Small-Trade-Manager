package com.uem.simple.manager.model;

import com.uem.simple.manager.model.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Funcionario {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    private String apelido;

    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "usuario_role")
    private Role role;

    private String rg;

    private String cpf;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime nascimento;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_cidade_id")
    private Cidade cidade;

    private String telefone;

    private String celular;

}
