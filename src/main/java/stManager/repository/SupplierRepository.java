package stManager.repository;

import java.util.List;

import stManager.model.Fornecedores;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Fornecedores, Long>{
    
    @Query("select a from Fornecedores a where a.nome like %?1%")
    List<Fornecedores> getAllByNomeFantasia(String nome);
}
