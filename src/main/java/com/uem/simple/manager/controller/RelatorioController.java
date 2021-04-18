package com.uem.simple.manager.controller;

import com.uem.simple.manager.service.BudgetService;
import com.uem.simple.manager.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
public class RelatorioController {
    
    @Autowired
    ProductService productService;

    @Autowired
    BudgetService budgetService;



    @GetMapping
    public String report (Model m){
        return "report/home";
    }


    @GetMapping("/prod_report")
    public String generateProductReport(Model m){
        m.addAttribute("relacaoProd", productService.findAllByQtd());
        m.addAttribute("numContador", productService.count());
        m.addAttribute("relacaoFiltrada", productService.listagemProdutosEmFalta());
        return "report/prod_report";
    }

    @GetMapping("/budget_report")
    public String generateBudgetReport(Model m){
        m.addAttribute("relacaoBudget", budgetService.findAllByData());
        m.addAttribute("totalAsc", budgetService.findAllByTotalAsc());
        m.addAttribute("totalDesc", budgetService.findAllByTotalDesc());
        return "report/budget_report";
    }
}
