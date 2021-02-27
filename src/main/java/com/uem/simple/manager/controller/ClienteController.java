package com.uem.simple.manager.controller;

import com.uem.simple.manager.model.Cidade;
import com.uem.simple.manager.model.Cliente;
import com.uem.simple.manager.repository.CidadeRepository;
import com.uem.simple.manager.repository.ClienteRepository;
import com.uem.simple.manager.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/client")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;
    private final CidadeRepository cidadeRepository;

    @Autowired
    public ClienteController(ClienteService clienteService, ClienteRepository cr, ClienteRepository clienteRepository, CidadeRepository cidadeRepository) {
        this.clienteService = clienteService;
        this.clienteRepository = clienteRepository;
        this.cidadeRepository = cidadeRepository;
    }

    @GetMapping
    public String client(Model m) {
        m.addAttribute("clientes", clienteService.findAll());
        return "client/home";
    }

    @GetMapping("/new")
    public String addClient(Model m) {
        Cliente cliente = new Cliente();
        m.addAttribute("clientes", cliente);
        return "client/new";
    }

    @PostMapping("/add")
    public String salvaClient(@Valid Cliente cliente, BindingResult br, RedirectAttributes ra) {
        if (br.hasErrors()){
            return "client/new";
        }
        Cidade cidade = new Cidade();
        cidade.setEstado(cliente.getEndereco().getCidade().getEstado());
        cidade.setNome(cliente.getEndereco().getCidade().getNome());
        cidadeRepository.saveAndFlush(cidade);
        cliente.getEndereco().setCidade(cidade);
        clienteRepository.saveAndFlush(cliente);
        return "redirect:/client";
    }

}
