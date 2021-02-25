package com.uem.manager.repository;

import com.uem.manager.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Produto, Long>{
    
}
