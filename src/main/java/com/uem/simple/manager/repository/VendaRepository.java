package com.uem.simple.manager.repository;

import com.uem.simple.manager.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
