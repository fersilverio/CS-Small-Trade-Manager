package com.uem.simple.manager.controller;

import java.util.List;

import javax.validation.Valid;

import com.uem.simple.manager.model.Fornecedor;
import com.uem.simple.manager.model.Produto;
import com.uem.simple.manager.repository.ProductRepository;
import com.uem.simple.manager.repository.SupplierRepository;
import com.uem.simple.manager.service.ProductService;
import com.uem.simple.manager.service.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final SupplierService supplierService;

    
    @Autowired
    public ProductController (ProductService productService, SupplierRepository supplierRepository, ProductRepository productRepository, SupplierService supplierService){
        this.productService = productService;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.supplierService = supplierService;
    }
    
    
    
    @GetMapping
    public String product (Model m){
        m.addAttribute("ListaProdutos", productService.findAll());
        return "product/home";
    }


    @GetMapping("/new")
    public String addProduct(Model m){
        Produto product = new Produto();
        m.addAttribute("produto", product);
        m.addAttribute("listaFornecedores", supplierService.findAll());
        return "product/new";
    }


    @PostMapping("/add")
    public String saveProduct(@Valid Produto product, BindingResult br, RedirectAttributes ra){
        if (br.hasErrors()){
            return "product/new";
        }
        productRepository.save(product);
        return "redirect:/product";
    }


    @GetMapping("/view/{id}")
    public String viewProduct(@PathVariable Long id, Model m){
        Produto product = productService.getProductById(id);
        m.addAttribute("produto", product);
        m.addAttribute("update", false);
        return "product/manage";
    }


    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model m) {
        Produto product = productService.getProductById(id);
        m.addAttribute("produto", product);
        m.addAttribute("update", true);
        return "product/manage";
    }



    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Produto product = productService.getProductById(id);
        productRepository.delete(product);
        return "redirect:/product";
    }

}
