package com.uem.simple.manager.model.enums;

public enum Ativo {
    S("Sim"), N("Não");

    private String ativo;

    public String getAtivo(){
        return ativo;
    }

    Ativo (String ativo){
        this.ativo = ativo;
    }
}
