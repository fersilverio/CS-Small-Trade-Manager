package com.uem.simple.manager.controller;

import javax.validation.Valid;

import com.uem.simple.manager.model.Servico;
import com.uem.simple.manager.repository.ServicoRepository;
import com.uem.simple.manager.service.ServicoService;

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
@RequestMapping("/service")
public class ServicoController {
    private final ServicoRepository servicoRepository;
    private final ServicoService servicoService;

    @Autowired
    public ServicoController(ServicoRepository servicoRepository, ServicoService servicoService){
        this.servicoRepository = servicoRepository;
        this.servicoService = servicoService;
    }

    @GetMapping
    public String service (Model m){
        m.addAttribute("ListaServicos", servicoService.findAll());
        return "service/home";
    }

    @GetMapping("/new")
    public String addService(Model m) {
        Servico servico = new Servico();
        m.addAttribute("servico", servico);
        return "service/new";
    }

    @PostMapping("/add")
    public String salvaServico(@Valid Servico servico, BindingResult br, RedirectAttributes ra){
        if (br.hasErrors()){
            return "service/new";
        }
        servicoRepository.save(servico);
        return "redirect:/service";
    }

    @GetMapping("/view/{id}")
    public String viewService(@PathVariable Long id, Model m) {
        Servico servico = servicoService.getServicoById(id);
        m.addAttribute("servico", servico);
        m.addAttribute("update", false);
        return "service/manage";
    }

    @GetMapping("/edit/{id}")
    public String editSupplier(@PathVariable Long id, Model m) {
        Servico servico = servicoService.getServicoById(id);
        m.addAttribute("servico", servico);
        m.addAttribute("update", true);
        return "service/manage";
    }

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable Long id) {
        Servico servico = servicoService.getServicoById(id);
        servicoRepository.delete(servico);
        return "redirect:/service";
    }
}
