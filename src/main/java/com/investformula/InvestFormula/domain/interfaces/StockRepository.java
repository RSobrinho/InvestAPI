package com.investformula.InvestFormula.domain.interfaces;

import com.investformula.InvestFormula.domain.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
