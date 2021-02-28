package com.uem.manager.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.uem.manager.model.Funcionario;

@Repository
public interface EmployeeRepository extends JpaRepository<Funcionario, Long>{
    
    @Query("select a from Funcionario a where a.nome like %?1%")
    List<Funcionario> getAllByNome(String nome);
}
