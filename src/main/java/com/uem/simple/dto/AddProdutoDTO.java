package com.uem.simple.dto;

import com.uem.simple.manager.model.Produto;

public class AddProdutoDTO {
    private Produto produto;
    private Integer qtd;
    private Long id;

    public AddProdutoDTO() {
        
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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
