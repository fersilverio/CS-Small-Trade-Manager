package com.uem.simple.manager.repository;

import com.uem.simple.manager.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
