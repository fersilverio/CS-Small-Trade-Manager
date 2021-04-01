package com.uem.simple.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import com.uem.simple.manager.model.Cidade;
import com.uem.simple.manager.model.Fornecedor;
import com.uem.simple.manager.repository.CidadeRepository;
import com.uem.simple.manager.repository.SupplierRepository;
import com.uem.simple.manager.service.SupplierService;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

    private final SupplierService supplierService;
    private final SupplierRepository supplierRepository;
    private final CidadeRepository cidadeRepository;

    @Autowired
    public SupplierController(SupplierService supplierService, SupplierRepository supplierRepository, CidadeRepository cidadeRepository) {
        this.supplierService = supplierService;
        this.supplierRepository = supplierRepository;
        this.cidadeRepository = cidadeRepository;
    }

    @GetMapping
    public String supplier(Model m) {
        m.addAttribute("Listafornecedores", supplierService.findAll());
        return "supplier/home";
    }

    @GetMapping("/new")
    public String addSupplier(Model m) {
        Fornecedor supplier = new Fornecedor();
        m.addAttribute("fornecedor", supplier);
        return "supplier/new";
    }

    @PostMapping("/add")
    public String salvaSupplier(@Valid Fornecedor supplier, BindingResult br, RedirectAttributes ra) {
        if (br.hasErrors()) {
            return "supplier/new";
        }
        Cidade cidade = new Cidade();
        cidade.setEstado(supplier.getEndereco().getCidade().getEstado());
        cidade.setNome(supplier.getEndereco().getCidade().getNome());
        cidadeRepository.saveAndFlush(cidade);
        supplier.getEndereco().setCidade(cidade);
        supplierRepository.saveAndFlush(supplier);
        return "redirect:/supplier";
    }

    @GetMapping("/view/{id}")
    public String viewSupplier(@PathVariable Long id, Model m) {
        Fornecedor supplier = supplierService.getSupplierById(id);
        m.addAttribute("fornecedor", supplier);
        m.addAttribute("update", false);
        return "supplier/manage";
    }

    @GetMapping("/edit/{id}")
    public String editSupplier(@PathVariable Long id, Model m) {
        Fornecedor supplier = supplierService.getSupplierById(id);
        m.addAttribute("fornecedor", supplier);
        m.addAttribute("update", true);
        return "supplier/manage";
    }

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable Long id) {
        Fornecedor supplier = supplierService.getSupplierById(id);
        supplierRepository.delete(supplier);
        return "redirect:/supplier";
    }

}
