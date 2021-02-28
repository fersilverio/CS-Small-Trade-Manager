package com.uem.manager.service;

import java.util.List;

import com.uem.manager.model.Funcionario;

public interface FuncionarioService {
    List<Funcionario> getAllEmployees();
    void saveEmployee(Funcionario funcionario);
    Funcionario getEmployeeById(long id);
    void deleteEmployeeById(long id);
    List<Funcionario> getAllEmployeesByNome(String nome);
}
