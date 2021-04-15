
package com.uem.simple.manager.service;

import java.util.List;
import java.util.Optional;

import com.uem.simple.manager.model.Orcamento;
import com.uem.simple.manager.repository.OrcamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {
    
    private final OrcamentoRepository or;

    @Autowired
    public BudgetService(OrcamentoRepository or){
        this.or = or;
    }

    public List<Orcamento> findAll(){
        return or.findAll();
    }

    public Orcamento getBudgetById(Long id){
        Optional<Orcamento> budgetOpc = or.findById(id);
        Orcamento budget = null;
        if(budgetOpc.isPresent()){
            budget = budgetOpc.get();
        }
        else{
            throw new RuntimeException("Budget not found for ID: " + id);
        }
        return budget;
    }

}
