package com.uem.manager.controller;

import com.uem.manager.model.Produto;
import com.uem.manager.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    @Autowired
    private ProdutoService produtoService;

    // Listagem de produtos
    @GetMapping("/")
    public String viewProdutoMainPage(Model model) {
        model.addAttribute("productList", produtoService.getAllProducts());
        return "index";
    }

    // CREATE
    @GetMapping("/showNewProductForm")
    public String showNewProductForm(Model model) {
        Produto produto = new Produto();
        model.addAttribute("produto", produto);
        return "add_product";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("produto") Produto produto) {
        produtoService.saveProduct(produto);
        return "redirect:/";
    }

    // UPDATE
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Produto produto = produtoService.getProductById(id);
        model.addAttribute("produto", produto);
        return "manage_product";
    }


    @GetMapping("/deleteProductById/{id}")
    public String deleteProductById(@PathVariable(value = "id") long id, Model model) {
        this.produtoService.deleteProductById(id);
        return "redirect:/";
    }
}
