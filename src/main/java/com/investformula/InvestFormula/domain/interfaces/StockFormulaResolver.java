package com.investformula.InvestFormula.domain.interfaces;

import com.investformula.InvestFormula.domain.stock.StockFormulas;
import com.investformula.InvestFormula.domain.stock.StockProperties;

public interface StockFormulaResolver {
    StockFormulas getFormulasBy(StockProperties properties);
}
