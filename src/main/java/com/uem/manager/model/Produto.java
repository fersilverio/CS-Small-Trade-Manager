package com.uem.manager.model;

//import com.uem.simple.manager.model.enums.Origem;
import lombok.Data;
import lombok.EqualsAndHashCode;
//import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
//import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "produto")
public class Produto {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


	@Column(name = "nome")
    private String nome;
	/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_fornecedor_id")
    private Fornecedor fornecedor;
	*/
	@Column(name = "unidade")
    private String unidade;

	@Column(name = "preco_bruto")
    private BigDecimal precoBruto;

	@Column(name = "preco_liquido")
    private BigDecimal precoLiquido;

	@Column(name = "estoque_min")
    private Integer estoqueMin;

	@Column(name = "estoque_max")
    private Integer estoqueMax;

	@Column(name = "qtd")
    private Integer qtd;

	@Column(name = "peso")
    private Double peso;

	@Column(name = "ativo")
    private Boolean ativo;
	/*
    @Enumerated(EnumType.STRING)
    private Origem origem;
	*/
	/*
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime",name = "carga_horaria")
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
    */
    
    // Gets e Sets
    

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

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

	public Boolean isAtivo() {
		return this.ativo;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
    
    
}
