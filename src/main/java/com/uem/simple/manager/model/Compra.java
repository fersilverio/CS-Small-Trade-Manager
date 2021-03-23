package com.uem.simple.manager.model;

import com.uem.simple.manager.model.enums.FormaPagamento;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Compra {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compra_fornecedor_id")
    private Fornecedor fornecedor;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime data;

    @OneToMany(mappedBy = "compra")
    private List<Produto> produtos = new ArrayList<>();

    private Integer qtd;

    public Integer getQtd() {
        return this.qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    };

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    public FormaPagamento getFormaPagamento() {
        return this.formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    };

    private BigDecimal total;

    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    };

}
