package com.br.saks.Sprint02.sprint02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.saks.Sprint02.sprint02.model.Investimento;

public interface InvestimentoRepository extends JpaRepository<Investimento, Long>{
    
}
