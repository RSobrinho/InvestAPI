package com.investformula.InvestFormula.domain;

import com.investformula.InvestFormula.domain.interfaces.ExternalStockProperties;

public interface StockFormulaResolver {
    StockFormulas getFormulasBy(ExternalStockProperties properties);
}
