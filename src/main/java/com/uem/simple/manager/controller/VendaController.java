package com.uem.simple.manager.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;

import com.uem.simple.dto.AddProdutoDTO;
import com.uem.simple.dto.AddSalesDTO;
import com.uem.simple.manager.model.Venda;
import com.uem.simple.manager.model.ItemVenda;
import com.uem.simple.manager.model.Cliente;
import com.uem.simple.manager.model.Cidade;
import com.uem.simple.manager.repository.VendaRepository;
import com.uem.simple.manager.repository.ItemVendaRepository;
import com.uem.simple.manager.repository.ClienteRepository;
import com.uem.simple.manager.repository.CidadeRepository;
import com.uem.simple.manager.service.VendaService;
import com.uem.simple.manager.service.ClienteService;
import com.uem.simple.manager.service.ProductService;

@Controller
@RequestMapping("/venda")
public class VendaController {
    private final VendaService vendaService;
    private final VendaRepository vendaRepository;
    private final ItemVendaRepository itemVendaRepository;
    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;
    private final ProductService produtoService;

    
    @Autowired
    public VendaController (VendaService vendaService, VendaRepository vendaRepository,ItemVendaRepository itemVendaRepository, ClienteRepository clienteRepository, ClienteService clienteService, ProductService produtoService){
        this.vendaService = vendaService;
        this.vendaRepository = vendaRepository;
        this.itemVendaRepository = itemVendaRepository; 
        this.clienteRepository = clienteRepository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }
    @GetMapping
    public String product (Model m){
        m.addAttribute("ListaVendas", vendaService.findAll());
        return "venda/home";
    }

    @GetMapping("/new")
    public String addSale(Model m, @RequestParam(required = false) Long id){
        Venda sale = new Venda();
        AddSalesDTO salesDTO = new AddSalesDTO();
        if(id != null){
            sale = vendaRepository.findById(id).get();
            salesDTO.setId(id);
        }
        sale.getValorTotalItens();
        m.addAttribute("venda", sale);
        m.addAttribute("ListaProdutos", produtoService.findAll());
        m.addAttribute("ListaClientes", clienteService.findAll());
        m.addAttribute("vendaDTO", salesDTO);
        m.addAttribute("lista", sale.getItens());
        m.addAttribute("total", sale.getTotal());
        return "venda/new";
    }

    @PostMapping("/add")
    public String salvaBudget(@Valid Venda sale, BindingResult br, RedirectAttributes ra){
        if(br.hasErrors()){
            return "venda/new";
        }
        vendaRepository.saveAndFlush(sale);
        return "redirect:/venda";
    }

/* verifica se a quantidade excede o que tem no estoque, se não, adiciona o produto*/
@PostMapping("/add_sales")
public String addProduct(AddSalesDTO vendaDTO, RedirectAttributes ra){
    if(vendaDTO.getProduto().getEstoqueMax() < vendaDTO.getQtd()){
        ra.addFlashAttribute("aviso", "quantidade excede o limite em estoque!");
        return "redirect:/venda/new";
    }

    if(vendaDTO.getId() != null){
        Venda sale = vendaRepository.findById(vendaDTO.getId()).get();
        ItemVenda item = new ItemVenda();
        for(ItemVenda i : sale.getItens()){
            if(i.getProduto().getId() == vendaDTO.getProduto().getId()){
                i.setQtd(i.getQtd() + vendaDTO.getQtd());
                if(i.getQtd() < 1){
                    i.setQtd(1);
                }
                itemVendaRepository.save(i);
                sale.setData(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()).toString());
                sale.setCliente(vendaDTO.getCliente());
                sale.getValorTotalItens();
                return "redirect:/venda/new?id="+vendaDTO.getId();
            }
        }
        item.setProduto(vendaDTO.getProduto());
        item.setQtd(vendaDTO.getQtd());
        item.setPrecoUnitario(vendaDTO.getProduto().getPrecoBruto());
        item.setVenda(sale);
        itemVendaRepository.save(item);
        sale.getItens().add(item);
        sale.setCliente(vendaDTO.getCliente());
        sale.setData(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()).toString());
        sale.getValorTotalItens();
        vendaRepository.save(sale);
        return "redirect:/venda/new?id="+vendaDTO.getId();
    }

    else{
        Venda sale = new Venda();
        vendaRepository.save(sale);
        ItemVenda item = new ItemVenda();
        item.setProduto(vendaDTO.getProduto());
        item.setQtd(vendaDTO.getQtd());
        item.setPrecoUnitario(vendaDTO.getProduto().getPrecoBruto());
        item.setVenda(sale);
        itemVendaRepository.save(item);
        sale.getItens().add(item);
        sale.setCliente(vendaDTO.getCliente());
        sale.setData(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()).toString());
        sale.getValorTotalItens();
        vendaRepository.save(sale);
        return "redirect:/venda/new?id="+sale.getId();
        }
    }

    @GetMapping("/view/{id}")
    public String viewSale(@PathVariable Long id, Model m){
        Venda sale = vendaService.getVendaById(id);
        AddSalesDTO salesDTO = new AddSalesDTO();
        if(id != null){
            sale = vendaRepository.findById(id).get();
            salesDTO.setId(id);
        }
        sale.getValorTotalItens();
        m.addAttribute("venda", sale);
        m.addAttribute("ListaProdutos", produtoService.findAll());
        m.addAttribute("ListaClientes", clienteService.findAll());
        m.addAttribute("vendaDTO", salesDTO);
        m.addAttribute("cliente", sale.getCliente());
        m.addAttribute("lista", sale.getItens());
        m.addAttribute("total", sale.getTotal());
        m.addAttribute("update", false);
        return "venda/manage";
    }
    @GetMapping("/edit/{id}")
    public String editSale(@PathVariable Long id, Model m){
        Venda sale = vendaService.getVendaById(id);
        AddSalesDTO salesDTO = new AddSalesDTO();
        if(id != null){
            sale = vendaRepository.findById(id).get();
            salesDTO.setId(id);
        }
        sale.getValorTotalItens();
        m.addAttribute("venda", sale);
        m.addAttribute("ListaProdutos", produtoService.findAll());
        m.addAttribute("ListaClientes", clienteService.findAll());
        m.addAttribute("vendaDTO", salesDTO);
        m.addAttribute("cliente", sale.getCliente());
        m.addAttribute("lista", sale.getItens());
        m.addAttribute("total", sale.getTotal());
        m.addAttribute("update", true);
        return "venda/manage";
    }

    @GetMapping("/delete/{id}")
    public String deleteVenda(@PathVariable Long id, Model m){
        Venda sale = vendaService.getVendaById(id);
        vendaRepository.delete(sale);
        return "redirect:/venda";
    }

    @GetMapping("/delete_item/{idVenda}/{idItem}")
    public String deleteItem(@PathVariable Long idVenda, @PathVariable Long idItem, Model m){
        ItemVenda itens = itemVendaRepository.findById(idItem).get();
        itemVendaRepository.delete(itens);
        return "redirect:/venda/new?id="+idVenda;
    }

}