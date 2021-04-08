package com.uem.simple.manager.model.enums;

public enum TipoServico {
    CA("Cadastros e Acessos"),
    SI("Serviço de Impressão"),
    SRI("Serviços de Rede e Internet"),
    AS("Aplicativos e Softwares"),
    CP("Computadores e Periféricos"),
    ER("Eventos e Reuniões"),
    E("Empréstimos"),
    SC("Serviço de Consultoria"),
    SC1("Serviços de Conferência"),
    ST("Serviços de Telefonia"),
    SB("Sistemas Bancários"),
    STI("Serviços Técnicos de Infraestrutura"),
    SD("Solicitação de Desenvolvimento"),
    PW("Portais Web"),
    SC2("Sistemas Corporativos"),
    ABD("Administração de Banco de Dados"),
    GTI("Governança de TI");

    private String tipoServico;

    public String getTipoServico(){
        return tipoServico;
    }

    TipoServico (String tipoServico){
        this.tipoServico = tipoServico;
    }
}
