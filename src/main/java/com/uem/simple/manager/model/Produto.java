package com.uem.simple.manager.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import com.uem.simple.manager.model.enums.Origem;
import com.uem.simple.manager.model.enums.TipoProduto;
import com.uem.simple.manager.model.enums.Ativo;


import java.math.BigDecimal;

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
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "fornecedor")
    public Fornecedor fornecedor;
	 
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

	@Enumerated(EnumType.STRING)
	private Ativo ativo;

	@Enumerated(EnumType.STRING)
	private Origem origem;

	@Enumerated(EnumType.STRING)
	private TipoProduto tipoProduto;

	@Column(name = "carga_horaria")
	private String cargaHoraria;
	

	// Gets e Sets

	public Long getId() {
		return this.id;
	}

	public String getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
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


	public Origem getOrigem() {
		return this.origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}


	public Ativo getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}



	public TipoProduto getTipoProduto() {
		return this.tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}


	public Fornecedor getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

    
}
