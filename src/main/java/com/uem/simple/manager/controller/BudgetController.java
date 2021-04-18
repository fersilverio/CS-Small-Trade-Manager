
package com.uem.simple.manager.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import com.uem.simple.dto.AddProdutoDTO;
import com.uem.simple.manager.model.ItemOrcamento;
import com.uem.simple.manager.model.Orcamento;
import com.uem.simple.manager.repository.ItemOrcamentoRepository;
import com.uem.simple.manager.repository.OrcamentoRepository;
import com.uem.simple.manager.service.BudgetService;
import com.uem.simple.manager.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/budget")
public class BudgetController {
    
    private final BudgetService budgetService;
    private final OrcamentoRepository orcamentoRepository;
    private final ProductService produtoService;
    private final ItemOrcamentoRepository itemOrcamentoRepository;
    
    @Autowired
    public BudgetController(BudgetService budgetService, OrcamentoRepository orcamentoRepository, ProductService produtoService, ItemOrcamentoRepository itemOrcamentoRepository){
        this.budgetService = budgetService;
        this.orcamentoRepository = orcamentoRepository;
        this.produtoService = produtoService;
        this.itemOrcamentoRepository = itemOrcamentoRepository;        
    }

    @GetMapping
    public String budget(Model m){
        m.addAttribute("ListaOrcamentos", budgetService.findAll());
        return "budget/home";
    }

    @GetMapping("/new")
    public String addBudget(Model m, @RequestParam(required = false) Long id){
        Orcamento budget = new Orcamento();
        AddProdutoDTO produtoDTO = new AddProdutoDTO();
        if(id != null){
            budget = orcamentoRepository.findById(id).get();
            produtoDTO.setId(id);
        }
        budget.getValorTotalItens();
        m.addAttribute("orcamento", budget);
        m.addAttribute("ListaProdutos", produtoService.findAll());
        m.addAttribute("produtoDTO", produtoDTO);
        m.addAttribute("lista", budget.getItens());
        m.addAttribute("total", budget.getTotal());
        return "budget/new";
    }
    
    @PostMapping("/add")
    public String salvaBudget(@Valid Orcamento budget, BindingResult br, RedirectAttributes ra){
        if(br.hasErrors()){
            return "budget/new";
        }
        orcamentoRepository.saveAndFlush(budget);
        return "redirect:/budget";
    }

    /* verifica se a quantidade excede o que tem no estoque, se não, adiciona o produto*/
    @PostMapping("/add_product")
    public String addProduct(AddProdutoDTO produtoDTO, RedirectAttributes ra){
        if(produtoDTO.getProduto().getEstoqueMax() < produtoDTO.getQtd()){
            ra.addFlashAttribute("aviso", "quantidade excede o limite em estoque!");
            return "redirect:/budget/new";
        }
        if(produtoDTO.getId() != null){
            Orcamento orcamento = orcamentoRepository.findById(produtoDTO.getId()).get();
            ItemOrcamento item = new ItemOrcamento();
            for(ItemOrcamento i : orcamento.getItens()){
                if(i.getProduto().getId() == produtoDTO.getProduto().getId()){
                    i.setQtd(i.getQtd() + produtoDTO.getQtd());
                    if(i.getQtd() < 1){
                        i.setQtd(1);
                    }
                    itemOrcamentoRepository.save(i);
                    orcamento.setData(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()).toString());
                    orcamento.getValorTotalItens();
                    return "redirect:/budget/new?id="+produtoDTO.getId();
                }
            }
            item.setProduto(produtoDTO.getProduto());
            item.setQtd(produtoDTO.getQtd());
            item.setPrecoUnitario(produtoDTO.getProduto().getPrecoBruto());
            item.setOrcamento(orcamento);
            itemOrcamentoRepository.save(item);
            orcamento.getItens().add(item);
            orcamento.setData(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()).toString());
            orcamento.getValorTotalItens();
            orcamentoRepository.save(orcamento);
            return "redirect:/budget/new?id="+produtoDTO.getId();
        }
        else{
            Orcamento orcamento = new Orcamento();
            orcamentoRepository.save(orcamento);
            ItemOrcamento item = new ItemOrcamento();
            item.setProduto(produtoDTO.getProduto());
            item.setQtd(produtoDTO.getQtd());
            item.setPrecoUnitario(produtoDTO.getProduto().getPrecoBruto());
            item.setOrcamento(orcamento);
            itemOrcamentoRepository.save(item);
            orcamento.getItens().add(item);
            orcamento.setData(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()).toString());
            orcamento.getValorTotalItens();
            orcamentoRepository.save(orcamento);
            return "redirect:/budget/new?id="+orcamento.getId();
        }
    }

    @GetMapping("/view/{id}")
    public String viewBudget(@PathVariable Long id, Model m){
        Orcamento budget = budgetService.getBudgetById(id);
        AddProdutoDTO produtoDTO = new AddProdutoDTO();
        if(id != null){
            budget = orcamentoRepository.findById(id).get();
            produtoDTO.setId(id);
        }
        budget.getValorTotalItens();
        m.addAttribute("orcamento", budget);
        m.addAttribute("ListaProdutos", produtoService.findAll());
        m.addAttribute("produtoDTO", produtoDTO);
        m.addAttribute("lista", budget.getItens());
        m.addAttribute("total", budget.getTotal());
        m.addAttribute("update", false);
        return "budget/manage";
    }

    @GetMapping("/edit/{id}")
    public String editBudget(@PathVariable Long id, Model m){
        Orcamento budget = budgetService.getBudgetById(id);
        AddProdutoDTO produtoDTO = new AddProdutoDTO();
        if(id != null){
            budget = orcamentoRepository.findById(id).get();
            produtoDTO.setId(id);
        }
        budget.getValorTotalItens();
        m.addAttribute("orcamento", budget);
        m.addAttribute("ListaProdutos", produtoService.findAll());
        m.addAttribute("produtoDTO", produtoDTO);
        m.addAttribute("lista", budget.getItens());
        m.addAttribute("total", budget.getTotal());
        m.addAttribute("update", true);
        return "budget/manage";
    }

    @GetMapping("/delete/{id}")
    public String deleteBudget(@PathVariable Long id, Model m){
        Orcamento budget = budgetService.getBudgetById(id);
        orcamentoRepository.delete(budget);
        return "redirect:/budget";
    }

    @GetMapping("/delete_item/{idBudget}/{idItem}")
    public String deleteItem(@PathVariable Long idBudget, @PathVariable Long idItem, Model m){
        ItemOrcamento itens = itemOrcamentoRepository.findById(idItem).get();
        itemOrcamentoRepository.delete(itens);
        return "redirect:/budget/new?id="+idBudget;
    }

}
