package com.investformula.InvestFormula.infra.invest;

import com.investformula.InvestFormula.domain.stock.Stock;

public record InvestStockResponse(String stockName, StockFormulasResponse stockFormulasResponse) {
    public static InvestStockResponse from(Stock stock) {
        return new InvestStockResponse(stock.getStock(), StockFormulasResponse.from(stock.getFormulas()));
    }
}
