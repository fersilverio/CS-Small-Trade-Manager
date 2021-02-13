package com.uem.simple.manager.model;

import com.uem.simple.manager.model.enums.Origem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Produto {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_fornecedor_id")
    private Fornecedor fornecedor;

    private String unidade;

    private BigDecimal precoBruto;

    private BigDecimal precoLiquido;

    private Integer estoqueMin;

    private Integer estoqueMax;

    private Integer qtd;

    private Double peso;

    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Origem origem;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime cargaHoraria;

    @ManyToOne
    @JoinColumn(name = "produto_tipo_produto_id", nullable = false)
    private TipoProduto tipoProduto;

    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "orcamento_id", nullable = false)
    private Orcamento orcamento;

}
