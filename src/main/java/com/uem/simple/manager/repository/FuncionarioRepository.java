package com.uem.simple.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.uem.simple.manager.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByApelido(String apelido);

}