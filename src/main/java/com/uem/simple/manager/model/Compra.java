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

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    private BigDecimal total;

}
