package com.uem.simple.manager.repository;
import java.util.List;
import com.uem.simple.manager.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Produto, Long>{
    
    @Query(value = "select * from produto order by qtd ASC", nativeQuery = true)
    List<Produto> findAllByQtd();

    @Query(value = "select * from produto where qtd < estoque_min", nativeQuery = true)
    List<Produto> countProductsLowerThanMinimum();

    @Query(value = "select * from produto where qtd < estoque_min", nativeQuery = true)
    List<Produto> listProductsLowerThanMinimum();
}
