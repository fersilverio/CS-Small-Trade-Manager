package com.uem.simple.manager.repository;

import com.uem.simple.manager.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
