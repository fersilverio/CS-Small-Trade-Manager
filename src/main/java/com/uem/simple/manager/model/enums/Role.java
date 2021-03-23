package com.uem.simple.manager.model.enums;

public enum Role {

    ROLE_ADMINISTRADOR("Administrador"), ROLE_FUNCIONARIO("Funcionário");

    private String role;

    public String getRole() {
        return role;
    }

    Role(String role) {
        this.role = role;
    }

}
