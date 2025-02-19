package com.investformula.InvestFormula.domain;

import com.investformula.InvestFormula.domain.interfaces.StockProperties;

public interface StockFormulaResolver {
    StockFormulas getFormulasBy(StockProperties properties);
}
