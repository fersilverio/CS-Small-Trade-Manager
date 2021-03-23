package com.uem.simple.manager.repository;

import com.uem.simple.manager.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByApelido(String apelido);

}
