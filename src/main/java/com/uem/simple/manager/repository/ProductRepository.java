package com.uem.simple.manager.repository;
import com.uem.simple.manager.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Produto, Long>{
}
