package com.uem.manager.service;

import java.util.List;

import com.uem.manager.model.Orcamento;

public interface OrcamentoService {
    List<Orcamento> getAllBudgets();
    void saveBudget(Orcamento orcamento);
    Orcamento getBudgetById(long id);
    void deleteBudgetById(long id);
    List<Orcamento> getAllBudgetsByNome(String nome);
}
