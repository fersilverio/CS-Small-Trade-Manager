package com.uem.simple.manager.model.enums;

public enum Origem {

    I("Internacional"), N("Nacional");

    private String origem;

    public String getOrigem() {
        return origem;
    }

    Origem (String origem) {
        this.origem = origem;
    }
}
