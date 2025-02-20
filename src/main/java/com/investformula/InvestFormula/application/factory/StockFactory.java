package com.investformula.InvestFormula.application.factory;

import com.investformula.InvestFormula.domain.interfaces.DefaultFactory;
import com.investformula.InvestFormula.domain.stock.Stock;
import com.investformula.InvestFormula.domain.interfaces.StockFormulaResolver;
import com.investformula.InvestFormula.domain.interfaces.StockRepository;
import com.investformula.InvestFormula.domain.interfaces.StockRequest;

public class StockFactory implements DefaultFactory<Stock, StockRequest> {
    private final StockFormulaResolver stockFormulaResolver;

    private final StockRepository stockRepository;

    public StockFactory(StockFormulaResolver stockFormulaResolver, StockRepository stockRepository) {
        this.stockFormulaResolver = stockFormulaResolver;
        this.stockRepository = stockRepository;
    }

    public Stock create(StockRequest infoRequest) {
        return new Stock(infoRequest.stock(), infoRequest.sector(), infoRequest.volume(),
                stockFormulaResolver, stockRepository);
    }
}
