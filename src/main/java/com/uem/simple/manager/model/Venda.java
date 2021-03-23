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
public class Venda {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    };

    @ManyToOne
    @JoinColumn(name = "venda_cliente_id", nullable = false)
    private Cliente cliente;

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    };

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime data;

    public OffsetDateTime getData() {
        return this.data;
    }

    public void setData(OffsetDateTime data) {
        this.data = data;
    };

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itens = new ArrayList<>();

    public List<ItemVenda> getItens() {
        return this.itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
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

    public void calcularValorTotal() {
        getItens().forEach(ItemVenda::calcularPrecoTotal);

        this.total = getItens().stream()
                .map(item -> item.getPrecoTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
