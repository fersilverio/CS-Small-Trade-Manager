package com.uem.simple.manager.repository;
import java.util.List;
import com.uem.simple.manager.model.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
    @Query(value = "select * from orcamento order by data ASC", nativeQuery = true)
    List<Orcamento> findAllByData();

    @Query(value = "select * from orcamento order by total ASC", nativeQuery = true)
    List<Orcamento> findAllByTotalAsc();

    @Query(value = "select * from orcamento order by total DESC", nativeQuery = true)
    List<Orcamento> findAllByTotalDesc();
}
