package com.investformula.InvestFormula.domain;

import com.investformula.InvestFormula.domain.Stock;
import com.investformula.InvestFormula.domain.interfaces.StockRequest;

public class StockFactory {

    public StockFactory() {

    }

    public Stock simpleCreate(StockRequest infoRequest) {
        return new Stock(infoRequest.stock(), "", "", "", "", "", "");
    }

}
