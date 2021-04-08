package com.uem.simple.manager.repository;

import org.springframework.stereotype.Repository;

import com.uem.simple.manager.model.Servico;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long>{
}
