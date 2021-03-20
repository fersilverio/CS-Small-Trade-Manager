package com.uem.manager.controller;

import java.util.List;

import com.uem.manager.model.Orcamento;
import com.uem.manager.service.OrcamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BudgetController {
    @Autowired
    private OrcamentoService orcamentoService;

    //READ orçamento
    @GetMapping("/")
    public String viewOrcamentoMainPage(Model model, @Param("nome") String nome){
        List<Orcamento> listaOrcamentos = orcamentoService.getAllBudgetsByNome(nome);
        model.addAttribute("budgetList", listaOrcamentos);
        model.addAttribute("nome", nome);
        return "index";
    }

    //CREATE orçamento
    @GetMapping("/showNewBudgetForm")
    public String ShowNewBudgetForm(Model model){
        Orcamento orcamento = new Orcamento();
        model.addAttribute("orcamento", orcamento);
        return "add_budget";
    }

    @PostMapping("/saveBudget")
    public String saveBudget(@ModelAttribute("orcamento") Orcamento orcamento){
        orcamentoService.saveBudget(orcamento);
        return "redirect:/";
    }

    //UPDATE orçamento
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model){
        Orcamento orcamento = orcamentoService.getBudgetById(id);
        model.addAttribute("orcamento", orcamento);
        return "manage_budget";
    }

    //DELETE orçamento
    @GetMapping("/deleteBudgetById/{id}")
    public String deleteBudgetById(@PathVariable(value = "id") long id, Model model){
        this.orcamentoService.deleteBudgetById(id);
        return "redirect:/";
    }
}
