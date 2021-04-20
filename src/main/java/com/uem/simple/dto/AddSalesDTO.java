package com.uem.simple.dto;

import com.uem.simple.manager.model.Produto;
import com.uem.simple.manager.model.Cliente;

public class AddSalesDTO {
    private Produto produto;
    private Cliente cliente;
    private Integer qtd;
    private Long id;

    public AddSalesDTO() {
        
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getQtd() {
        return this.qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
