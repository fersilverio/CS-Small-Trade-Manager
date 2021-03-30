package com.uem.simple.manager.repository;

import java.util.List;

import com.uem.simple.manager.model.Fornecedor;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Fornecedor, Long>{
    
    @Query("select a from Fornecedor a where a.nome like %?1%")
    List<Fornecedor> getAllByNomeFantasia(String nome);
}
