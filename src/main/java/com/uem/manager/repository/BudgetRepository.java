package com.uem.manager.repository;

import java.util.List;

import com.uem.manager.model.Orcamento;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Orcamento, Long>{
    
    @Query("select a from Orcamento a where a.nome like %?1%")
    List<Orcamento> getAllByNome(String nome);
}
