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

import com.uem.simple.manager.service.EmployeeService;

import javax.validation.Valid;

import com.uem.simple.manager.model.Cidade;
import com.uem.simple.manager.model.Funcionario;
import com.uem.simple.manager.repository.CidadeRepository;
import com.uem.simple.manager.repository.FuncionarioRepository;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    
    private final EmployeeService employeeService;
    private final FuncionarioRepository funcionarioRepository;
    private final CidadeRepository cidadeRepository;
    
    @Autowired
    public EmployeeController(EmployeeService employeeService, FuncionarioRepository funcionarioRepository, CidadeRepository cidadeRepository){
        this.employeeService = employeeService;
        this.funcionarioRepository = funcionarioRepository;
        this.cidadeRepository = cidadeRepository;
    }

    @GetMapping
    public String employee(Model m){
        m.addAttribute("ListaFuncionarios", employeeService.findAll());
        System.out.println(employeeService.findAll());
        return "employee/home";
    }

    @GetMapping("/new")
    public String addEmployee(Model m){
        Funcionario employee = new Funcionario();
        m.addAttribute("funcionario", employee);
        return "employee/new";
    }

    @PostMapping("/add")
    public String salvaEmployee(@Valid Funcionario employee, BindingResult br, RedirectAttributes ra){
        if(br.hasErrors()){
            return "employee/new";
        }
        Cidade cidade = new Cidade();
        cidade.setEstado(employee.getCidade().getEstado());
        cidade.setNome(employee.getCidade().getNome());
        cidadeRepository.saveAndFlush(cidade);
        employee.setCidade(cidade);
        funcionarioRepository.saveAndFlush(employee);
        return "redirect:/employee";
    }

    @GetMapping("/view/{id}")
    public String viewEmployee(@PathVariable Long id, Model m){
        Funcionario employee = employeeService.getEmployeeById(id);
        m.addAttribute("funcionario", employee);
        m.addAttribute("update", false);
        return "employee/manage";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model m){
        Funcionario employee = employeeService.getEmployeeById(id);
        m.addAttribute("funcionario", employee);
        m.addAttribute("update", true);
        return "employee/manage";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, Model m){
        Funcionario employee = employeeService.getEmployeeById(id);
        funcionarioRepository.delete(employee);
        return "redirect:/employee";
    }
}
