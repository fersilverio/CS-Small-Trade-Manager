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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_fornecedor_id")
    private Fornecedor fornecedores;

    public Fornecedor getFornecedores() {
        return this.fornecedores;
    }

    public void setFornecedores(Fornecedor fornecedores) {
        this.fornecedores = fornecedores;
    }

    private String unidade;

    private BigDecimal precoBruto;

    private BigDecimal precoLiquido;

    private Integer estoqueMin;

    private Integer estoqueMax;

    private Integer qtd;

    private Double peso;

    private Boolean ativo;

    public String getUnidade() {
        return this.unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public BigDecimal getPrecoBruto() {
        return this.precoBruto;
    }

    public void setPrecoBruto(BigDecimal precoBruto) {
        this.precoBruto = precoBruto;
    }

    public BigDecimal getPrecoLiquido() {
        return this.precoLiquido;
    }

    public void setPrecoLiquido(BigDecimal precoLiquido) {
        this.precoLiquido = precoLiquido;
    }

    public Integer getEstoqueMin() {
        return this.estoqueMin;
    }

    public void setEstoqueMin(Integer estoqueMin) {
        this.estoqueMin = estoqueMin;
    }

    public Integer getEstoqueMax() {
        return this.estoqueMax;
    }

    public void setEstoqueMax(Integer estoqueMax) {
        this.estoqueMax = estoqueMax;
    }

    public Integer getQtd() {
        return this.qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Double getPeso() {
        return this.peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Boolean getAtivo() {
        return this.ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Enumerated(EnumType.STRING)
    private Origem origem;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime cargaHoraria;

    public OffsetDateTime getCargaHoraria() {
        return this.cargaHoraria;
    }

    public void setCargaHoraria(OffsetDateTime cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    @ManyToOne
    @JoinColumn(name = "produto_tipo_produto_id", nullable = false)
    private TipoProduto tipoProduto;

    public TipoProduto getTipoProduto() {
        return this.tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;

    public Compra getCompra() {
        return this.compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    @ManyToOne
    @JoinColumn(name = "orcamento_id", nullable = false)
    private Orcamento orcamento;

    public Orcamento getOrcamento() {
        return this.orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    };

}
