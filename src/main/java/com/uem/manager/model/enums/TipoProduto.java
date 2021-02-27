package com.uem.manager.model.enums;

public enum TipoProduto {
    PrF("Produto Físico"),
    INF("Info-produto"),
    SERV("Serviço");
    

    private String tipo;

    public String getTipo(){
        return tipo;
    }

    TipoProduto (String tipo){
        this.tipo = tipo;
    }


}
