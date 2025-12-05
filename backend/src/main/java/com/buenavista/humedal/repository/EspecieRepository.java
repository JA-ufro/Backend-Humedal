package com.buenavista.humedal.repository;

import com.buenavista.humedal.model.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Long> {
    List<Especie> findByHumedalId(Long humedalId);
}
