package com.investformula.InvestFormula.domain;

import com.investformula.InvestFormula.domain.interfaces.StockRequest;

public class StockFactory {
    private final StockFormulaResolver stockFormulaResolver;

    public StockFactory(StockFormulaResolver stockFormulaResolver) {

        this.stockFormulaResolver = stockFormulaResolver;
    }

    public Stock simpleCreate(StockRequest infoRequest) {
        return new Stock(infoRequest.stock(), infoRequest.sector(), infoRequest.volume(), stockFormulaResolver);
    }

}
