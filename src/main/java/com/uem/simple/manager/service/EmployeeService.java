package com.uem.simple.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.uem.simple.manager.model.Funcionario;
import com.uem.simple.manager.repository.FuncionarioRepository;

@Service
public class EmployeeService {
    
    private final FuncionarioRepository fr;

    @Autowired
    public EmployeeService(FuncionarioRepository fr){
        this.fr = fr;
    }

    public List<Funcionario> findAll(){
        return fr.findAll();
    }

    public Funcionario getEmployeeById(Long id){
        Optional<Funcionario> employeeOpc = fr.findById(id);
        Funcionario employee = null;
        if(employeeOpc.isPresent()){
            employee = employeeOpc.get();
        }
        else{
            throw new RuntimeException("Employee not found for ID: " + id);
        }
        return employee;
    }

}
