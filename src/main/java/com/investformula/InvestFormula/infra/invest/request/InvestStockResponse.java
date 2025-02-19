package com.investformula.InvestFormula.infra.invest.request;

import com.investformula.InvestFormula.domain.Stock;
import com.investformula.InvestFormula.domain.StockFormulas;

public record InvestStockResponse(String stockName, StockFormulas formulas) {
    public static InvestStockResponse from(Stock stock) {
        return new InvestStockResponse(stock.name(), stock.getFormulas());
    }
}
