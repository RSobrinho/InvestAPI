package com.investformula.InvestFormula.domain;

import com.investformula.InvestFormula.domain.interfaces.StockRequest;

public class StockFactory {
    private final StockFormulaResolver stockFormulaResolver;

    private final StockRepository stockRepository;

    public StockFactory(StockFormulaResolver stockFormulaResolver, StockRepository stockRepository) {
        this.stockFormulaResolver = stockFormulaResolver;
        this.stockRepository = stockRepository;
    }

    public Stock simpleCreate(StockRequest infoRequest) {
        return new Stock(infoRequest.stock(), infoRequest.sector(), infoRequest.volume(),
                stockFormulaResolver, stockRepository);
    }
}
