package com.uem.simple.manager.controller;

import java.util.List;

import com.uem.simple.manager.model.Produto;
import com.uem.simple.manager.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProdutoService produtoService;

    // Listagem de produtos
    @GetMapping
    public String viewProdutoMainPage(Model model, @Param("nome") String nome) {
        List<Produto> listaProdutos = produtoService.getAllProductsByNome(nome);
        model.addAttribute("productList", listaProdutos);
        model.addAttribute("nome", nome);
        return "product/home";
    }

    // CREATE
    @GetMapping("/new")
    public String showNewProductForm(Model model) {
        Produto produto = new Produto();
        model.addAttribute("produto", produto);
        return "product/new";
    }

    @PostMapping("/add")
    public String saveProduct(@ModelAttribute("produto") Produto produto) {
        produtoService.saveProduct(produto);
        return "redirect:/product";
    }

    // UPDATE
    @GetMapping("/update/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Produto produto = produtoService.getProductById(id);
        model.addAttribute("produto", produto);
        return "product/manage";
    }


    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable(value = "id") long id, Model model) {
        this.produtoService.deleteProductById(id);
        return "redirect:/product";
    }


}
