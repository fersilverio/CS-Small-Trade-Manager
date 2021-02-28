package com.uem.manager.controller;

import java.util.List;

import com.uem.manager.model.Funcionario;
import com.uem.manager.service.FuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class EmployeeController {
    @Autowired
    private FuncionarioService funcionarioService;

    
    @GetMapping("/")
    public String viewFuncionarioMainPage(Model model, @Param("nome") String nome){
        List<Funcionario> listaFuncionarios = funcionarioService.getAllEmployeesByNome(nome);
        model.addAttribute("employeeList", listaFuncionarios);
        model.addAttribute("nome", nome);
        return "index";
    }

    //CREATE funcionario
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){
        Funcionario funcionario = new Funcionario();
        model.addAttribute("funcionario", funcionario);
        return "add_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("funcionario") Funcionario funcionario){
        funcionarioService.saveEmployee(funcionario);
        return "redirect:/";
    }

    //UPDATE funcionario
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model){
        Funcionario funcionario = funcionarioService.getEmployeeById(id);
        model.addAttribute("funcionario", funcionario);
        return "manage_employee";
    }

    //DELETE funcionario
    @GetMapping("/deleteEmployeeById/{id}")
    public String deleteEmployeeById(@PathVariable(value = "id") long id, Model model){
        this.funcionarioService.deleteEmployeeById(id);
        return "redirect:/";
    }
}
