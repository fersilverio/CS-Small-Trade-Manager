package com.uem.manager.service;

import java.util.List;
import java.util.Optional;

import com.uem.manager.model.Funcionario;
import com.uem.manager.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class FuncionarioServiceImpl implements FuncionarioService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Funcionario> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Funcionario funcionario){
        this.employeeRepository.save(funcionario);
    }
    
    @Override
    public Funcionario getEmployeeById(long id){
        Optional<Funcionario> optional = employeeRepository.findById(id);
        Funcionario funcionario = null;
        if(optional.isPresent()){
            funcionario = optional.get();
        }
        else{
            throw new RuntimeException("Employee not found for id::" + id);
        }
        return funcionario;
    }

    @Override
    public void deleteEmployeeById(long id){
        this.employeeRepository.deleteById(id);
    }

    @Override
    public List<Funcionario> getAllEmployeesByNome(String nome){
        if(nome != null){
            return employeeRepository.getAllByNome(nome);
        }
        return employeeRepository.findAll();
    }
}
