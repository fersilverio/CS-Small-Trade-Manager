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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    @GetMapping
    public String client(Model m) {
        return "purchase/home";
    }
}
