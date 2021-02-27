package com.uem.manager.repository;

import java.util.List;

import com.uem.manager.model.Produto;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Produto, Long>{
    
    @Query("select a from Produto a where a.nome like %?1%")
    List<Produto> getAllByNome(String nome);
}
