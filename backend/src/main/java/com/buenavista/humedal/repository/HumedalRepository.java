package com.buenavista.humedal.repository;

import com.buenavista.humedal.model.Humedal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumedalRepository extends JpaRepository<Humedal, Long> {
}
