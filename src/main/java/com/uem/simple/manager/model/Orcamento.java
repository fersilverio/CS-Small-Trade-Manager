
package com.uem.simple.manager.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Orcamento {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL)
    private List<ItemOrcamento> itens = new ArrayList<>();

    private String data;

    private BigDecimal total;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemOrcamento> getItens() {
        return this.itens;
    }

    public void setItens(List<ItemOrcamento> itens) {
        this.itens = itens;
    }
    

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void getValorTotalItens() {
		this.total = getItens().stream()
				.map(ItemOrcamento::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}

}
