package com.uem.simple.manager.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemVenda {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal precoUnitario;

    private BigDecimal precoTotal;

    private Integer qtd;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Venda venda;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;

    public void calcularPrecoTotal() {
        BigDecimal precoUnitario = this.getPrecoUnitario();
        Integer qtd = this.getQtd();

        if (precoUnitario == null) {
            precoUnitario = BigDecimal.ZERO;
        }

        if (qtd == null) {
            qtd = 0;
        }

        this.setPrecoTotal(precoUnitario.multiply(new BigDecimal(qtd)));
    }
}
