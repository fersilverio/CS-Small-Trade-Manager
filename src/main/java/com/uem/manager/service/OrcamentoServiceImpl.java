package com.uem.manager.service;

import java.util.List;
import java.util.Optional;

import com.uem.manager.model.Orcamento;
import com.uem.manager.repository.BudgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrcamentoServiceImpl implements OrcamentoService{
    @Autowired
    private BudgetRepository budgetRepository;

    @Override
    public List<Orcamento> getAllBudgets(){
        return budgetRepository.findAll();
    }

    @Override
    public void saveBudget(Orcamento orcamento){
        this.budgetRepository.save(orcamento);
    }

    @Override
    public Orcamento getBudgetById(long id){
        Optional<Orcamento> optional = budgetRepository.findById(id);
        Orcamento orcamento = null;
        if(optional.isPresent()){
            orcamento = optional.get();
        }
        else{
            throw new RuntimeException("Budget not found for id::" + id);
        }
        return orcamento;
    }

    @Override
    public void deleteBudgetById(long id){
        this.budgetRepository.deleteById(id);
    }

    @Override
    public List<Orcamento> getAllBudgetsByNome(String nome){
        if(nome != null){
            return budgetRepository.getAllByNome(nome);
        }
        return budgetRepository.findAll();
    }
}
