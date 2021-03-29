package com.uem.simple.manager.controller;

import javax.validation.Valid;

import com.uem.simple.manager.model.Orcamento;
import com.uem.simple.manager.repository.CidadeRepository;
import com.uem.simple.manager.repository.OrcamentoRepository;
import com.uem.simple.manager.service.BudgetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/budget")
public class BudgetController {
    
    private final BudgetService budgetService;
    private final OrcamentoRepository orcamentoRepository;
    
    @Autowired
    public BudgetController(BudgetService budgetService, OrcamentoRepository orcamentoRepository){
        this.budgetService = budgetService;
        this.orcamentoRepository = orcamentoRepository;        
    }

    @GetMapping
    public String budget(Model m){
        m.addAttribute("ListaOrcamentos", budgetService.findAll());
        return "budget/home";
    }

    @GetMapping("/new")
    public String addBudget(Model m){
        Orcamento budget = new Orcamento();
        m.addAttribute("orcamento", budget);
        return "budget/new";
    }

    @PostMapping("/add")
    public String salvaBudget(@Valid Orcamento budget, BindingResult br, RedirectAttributes ra){
        if(br.hasErrors()){
            return "budget/new";
        }
        //aqui tem que fazer a parte usando Cidade?     
    }

    @GetMapping("/view/{id}")
    public String viewBudget(@PathVariable Long id, Model m){
        Orcamento budget = budgetService.getBudgetById(id);
        m.addAttribute("orcamento", budget);
        m.addAttribute("uodate", false);
        return "budget/manage";
    }

    @GetMapping("/edit/{id}")
    public String editBudget(@PathVariable Long id, Model m){
        Orcamento budget = budgetService.getBudgetById(id);
        orcamentoRepository.delete(budget);
        return "redirect:/budget";
    }

}
