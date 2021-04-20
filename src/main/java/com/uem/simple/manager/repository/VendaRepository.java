package com.uem.simple.manager.repository;

import java.util.List;

import com.uem.simple.manager.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    @Query(value = "select * from venda order by data ASC", nativeQuery = true)
    List<Venda> findAllByData();
}
