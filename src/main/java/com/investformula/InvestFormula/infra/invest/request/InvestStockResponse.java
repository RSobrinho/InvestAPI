package com.investformula.InvestFormula.infra.invest.request;

import com.investformula.InvestFormula.domain.Stock;
import com.investformula.InvestFormula.infra.StockFormulasResponse;

public record InvestStockResponse(String stockName, StockFormulasResponse stockFormulasResponse) {
    public static InvestStockResponse from(Stock stock) {
        return new InvestStockResponse(stock.getStock(), StockFormulasResponse.from(stock.getFormulas()));
    }
}
